package com.github.ahmadaghazadeh.editor.widget;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.ahmadaghazadeh.editor.R;
import com.github.ahmadaghazadeh.editor.document.commons.LinesCollection;
import com.github.ahmadaghazadeh.editor.keyboard.ExtendedKeyboard;
import com.github.ahmadaghazadeh.editor.processor.TextProcessor;
import com.github.ahmadaghazadeh.editor.processor.language.Language;
import com.github.ahmadaghazadeh.editor.processor.language.LanguageProvider;
import com.github.ahmadaghazadeh.editor.processor.language.SupportedLanguage;
import com.github.ahmadaghazadeh.editor.processor.utils.DefaultSetting;

public class CodeEditor extends FrameLayout {
    FrameLayout rootView;
    boolean isReadOnly = false;
    boolean isShowExtendedKeyboard = false;
    int preHeight = 0;

    private TextProcessor editor;
    private LinesCollection lineNumbers;
    private Editable text;
    private ExtendedKeyboard recyclerView;
    private ICodeEditorTextChange codeEditorTextChange;

    public CodeEditor(Context context) {
        super(context);
        init(context, null);
    }

    public CodeEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CodeEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CodeEditor(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, null);
    }

    @BindingAdapter(value = {"code", "lang", "isReadOnly", "isShowExtendedKeyboard"})
    public static void setCodeView(CodeEditor view, MutableLiveData<String> code,
                                   MutableLiveData<SupportedLanguage> lang,
                                   boolean isReadOnly,
                                   boolean isShowExtendedKeyboard) {
        if (view == null) {
            return;
        }
        if (code != null) {
            view.setText(code.getValue(), 1);
        }
        if (lang != null) {
            view.setLanguage(LanguageProvider.INSTANCE.of(lang.getValue()));
        }
        view.setReadOnly(isReadOnly);
        view.setShowExtendedKeyboard(isShowExtendedKeyboard);

    }

    public void setOnTextChange(ICodeEditorTextChange onTextChange) {
        codeEditorTextChange = onTextChange;
        editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (codeEditorTextChange != null) {
                    codeEditorTextChange.onTextChange(s.toString());
                }
            }
        });
    }

    private void init(Context context, AttributeSet attrs) {
        try {
            initEditor();
            String code = "";
            SupportedLanguage lang = SupportedLanguage.JAVASCRIPT;
            isReadOnly = false;
            isShowExtendedKeyboard = false;
            if (attrs != null) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CodeEditor, 0, 0);
                if (a.hasValue(R.styleable.CodeEditor_code)) {
                    code = a.getString(R.styleable.CodeEditor_code);
                }
                isReadOnly = a.getBoolean(R.styleable.CodeEditor_isReadOnly, false);
                isShowExtendedKeyboard = a.getBoolean(R.styleable.CodeEditor_isShowExtendedKeyboard, true);
                a.recycle();

            }
            FrameLayout.LayoutParams rootViewParam = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            rootViewParam.gravity = Gravity.BOTTOM;
            rootView = new FrameLayout(context);
            rootView.setLayoutParams(rootViewParam);
            GutterView gutterView = new GutterView(context);
            LinearLayout.LayoutParams paramsGutter = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            //paramsGutter.alignWithParent = true;
            paramsGutter.gravity = Gravity.START;
            gutterView.setLayoutParams(paramsGutter);
            rootView.addView(gutterView);


            editor = new TextProcessor(context);
            FrameLayout.LayoutParams paramsTxtprocessor = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            editor.setLayoutParams(paramsTxtprocessor);
            editor.setScrollBarStyle(SCROLLBARS_OUTSIDE_INSET);
            editor.setGravity(Gravity.TOP | Gravity.START);

            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ThemeAttributes, 0, 0);
            try {
                int colorResource = a.getColor(R.styleable.ThemeAttributes_colorDocBackground, getResources().getColor(R.color.colorDocBackground));
                editor.setBackgroundColor(colorResource);
            } finally {
                a.recycle();
            }

            a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ThemeAttributes, 0, 0);
            try {
                int colorResource = a.getColor(R.styleable.ThemeAttributes_colorDocText, getResources().getColor(R.color.colorDocText));
                editor.setTextColor(colorResource);
            } finally {
                a.recycle();
            }


            editor.setLayerType(LAYER_TYPE_SOFTWARE, new TextPaint());
            rootView.addView(editor);

            editor.init(this);
            editor.setReadOnly(isReadOnly);

            FastScrollerView mFastScrollerView = new FastScrollerView(context);
            FrameLayout.LayoutParams fastParam = new FrameLayout.LayoutParams(30, LayoutParams.MATCH_PARENT);
            //fastParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
            fastParam.gravity = Gravity.END;

            mFastScrollerView.setLayoutParams(fastParam);
            rootView.addView(mFastScrollerView);
            mFastScrollerView.link(editor); //подключаем FastScroller к редактору

            gutterView.link(editor, lineNumbers); //подключаем Gutter к редактору
            LinesCollection lines = new LinesCollection();
            lines.add(0, 0);
            setLanguage(LanguageProvider.INSTANCE.of(lang)); //ставим язык
            setText(code, 1); //заполняем поле текстом
            setLineStartsList(lines); //подгружаем линии
            refreshEditor(); //подключаем все настройки
            editor.enableUndoRedoStack();

            recyclerView = new ExtendedKeyboard(context);


            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int height = rootView.getHeight(); //height is ready
                    if (preHeight != height) {

                        FrameLayout.LayoutParams recyclerViewParam = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        recyclerViewParam.setMargins(0, height - 100, 0, 0);
                        recyclerView.setLayoutParams(recyclerViewParam);
                        paramsTxtprocessor.setMargins(0, 0, 0, 100);
                        editor.setLayoutParams(paramsTxtprocessor);
                        preHeight = height ;
                    }

                }
            });


            recyclerView.setListener((view, symbol) -> {
                        if (symbol.getShowText().endsWith("End")) {
                            String text = editor.getText().toString();
                            int x = text.indexOf("\n", editor.getSelectionStart());
                            if (x == -1) {
                                x = text.length();
                            }
                            editor.setSelection(x);

                        } else {
                            editor.getText().insert(editor.getSelectionStart(), symbol.getWriteText());
                        }

                    }
            );
            setShowExtendedKeyboard(isShowExtendedKeyboard);
            rootView.addView(recyclerView);
            addView(rootView);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void setShowExtendedKeyboard(Boolean showExtendedKeyboard) {
        if (recyclerView != null) {
            recyclerView.setVisibility(showExtendedKeyboard ? VISIBLE : GONE);
        }
    }

    public void refreshEditor() {
        if (editor != null) {
            editor.setTextSize(DefaultSetting.INSTANCE.getFontSize());
            editor.setHorizontallyScrolling(!DefaultSetting.INSTANCE.getWrapContent());
            editor.setShowLineNumbers(DefaultSetting.INSTANCE.getShowLineNumbers());
            editor.setBracketMatching(DefaultSetting.INSTANCE.getBracketMatching());
            editor.setHighlightCurrentLine(DefaultSetting.INSTANCE.getHighlightCurrentLine());
            editor.setCodeCompletion(DefaultSetting.INSTANCE.getCodeCompletion());
            editor.setInsertBrackets(DefaultSetting.INSTANCE.getInsertBracket());
            editor.setIndentLine(DefaultSetting.INSTANCE.getIndentLine());
            editor.refreshTypeface();
            editor.refreshInputType();
        }
    }

    private void initEditor() {
        lineNumbers = new LinesCollection();
    }

    public String getText() {
        if (text != null)
            return text.toString();
        else
            return "";
    }

    @UiThread
    @Nullable
    public Language getLanguage() {
        return this.editor.getLanguage();
    }

    public void setLanguage(@NonNull Language language) {
        this.editor.setLanguage(language);
    }

    //region METHODS_DOC

    /**
     * Методы для редактора, чтобы менять их в "Runtime".
     */

    public void setReadOnly(boolean readOnly) {
        if (editor != null)
            editor.setReadOnly(readOnly);
    }

    // endregion METHODS_DOC

    //region LINES

    public void setLineStartsList(LinesCollection list) {
        lineNumbers = list;
    }

    public LinesCollection getLinesCollection() {
        return lineNumbers;
    }

    public int getLineCount() {
        return lineNumbers.getLineCount();
    }

    public int getLineForIndex(int index) {
        return lineNumbers.getLineForIndex(index);
    }

    public int getIndexForStartOfLine(int line) {
        return lineNumbers.getIndexForLine(line);
    }

    public int getIndexForEndOfLine(int line) {
        if (line == getLineCount() - 1) {
            return text.length();
        }
        return lineNumbers.getIndexForLine(line + 1) - 1;
    }

    public void replaceText(int start, int end, Editable text) {
        replaceText(start, end, text.toString());
    }

    public void replaceText(int start, int end, String text) {
        int i;
        if (this.text == null) {
            this.text = Editable.Factory.getInstance().newEditable("");
        }
        if (end >= this.text.length()) {
            end = this.text.length();
        }
        int newCharCount = text.length() - (end - start);
        int startLine = getLineForIndex(start);
        for (i = start; i < end; i++) {
            if (this.text.charAt(i) == '\n') {
                lineNumbers.remove(startLine + 1);
            }
        }
        lineNumbers.shiftIndexes(getLineForIndex(start) + 1, newCharCount);
        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lineNumbers.add(getLineForIndex(start + i) + 1, (start + i) + 1);
            }
        }
        if (start > end) {
            end = start;
        }
        if (start > this.text.length()) {
            start = this.text.length();
        }
        if (end > this.text.length()) {
            end = this.text.length();
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        this.text.replace(start, end, text);
    }

    public void setText(String text, int flag) {
        if (text != null) {
            setText(Editable.Factory.getInstance().newEditable(text), flag);
        } else {
            setText(Editable.Factory.getInstance().newEditable(""), flag);
        }
    }

    public void setText(Editable text, int flag) {
        if (flag == 1) {
           // this.text = text;
            if (editor != null)
                editor.setText(text);
            return;
        }
        int length = 0;
        if (text != null) {
            length = text.length();
        }
        replaceText(0, length, text);
    }

    //endregion LINES


    public interface ICodeEditorTextChange {
        void onTextChange(String str);
    }
}