package com.example.nguyenvantuan.testapp.util;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenvantuan on 7/15/15.
 */
public class PassCode {
    private OnPassCodeChangeListener passCodeChangeListener;
    protected Context context;
    private ViewGroup viewGroup;
    private ArrayList<EditText> editTexts;
    private boolean nextAction = true;

    public PassCode(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
        init();
    }

    public void setOnChangeListener(OnPassCodeChangeListener listener) {
        this.passCodeChangeListener = listener;
    }

    public boolean isValiPassCode(String textPassCode) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^([0-9])";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(textPassCode);
        return matcher.matches();
    }

    private void init() {

        editTexts = new ArrayList<>();

        for (int index = 0; index < viewGroup.getChildCount(); index++) {

            final View view = viewGroup.getChildAt(index);
                editTexts.add((EditText) view);
                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_UP) {
                            View nextFocusView = null;
                            if (keyCode == KeyEvent.KEYCODE_DEL) {
                                nextFocusView = getNextFocus(v, false);
                                if (((EditText) v).getText().toString().length() == 0 && nextFocusView != null) {
                                    ((EditText) nextFocusView).setText("");
                                } else {
                                    ((EditText) v).setText("");
                                }
                            } else if (isValiPassCode(((EditText) v).getText().toString())) {
                                nextFocusView = getNextFocus(v, true);
                            }
                            if (nextFocusView != null) {
                                nextFocusView.requestFocus();
                            }
                            if (getPassCode().length() == 4 && nextAction) {
                                nextAction = false;
                                passCodeChangeListener.onChange(getPassCode());
                            }
                        }
                        return false;
                    }
                });
            }

    }

    private View getNextFocus(View currentView, boolean isFocusRight) {
        View nextFocus = null;
        for (int i = 0; i < editTexts.size(); i++) {
            if (currentView == editTexts.get(i)) {
                int nextIndex;
                if (isFocusRight) {
                    nextIndex = i + 1;
                } else {
                    nextIndex = i - 1;
                }

                if (nextIndex >= 0 && nextIndex < editTexts.size()) {
                    nextFocus = editTexts.get(nextIndex);
                }
                break;
            }
        }

        return nextFocus;
    }

    public String getPassCode() {
        StringBuilder sb = new StringBuilder();
        for (EditText edt : editTexts) {
            sb.append(edt.getText().toString().trim());
        }
        return sb.toString();
    }

    public void clear() {
        for (EditText edt : editTexts) {
            edt.setText("");
        }
        requestFocus(0);
        nextAction = true;
    }

    public void requestFocus(int position) {
        editTexts.get(position).requestFocus();
    }

    public interface OnPassCodeChangeListener {
        void onChange(String passCode);
    }
}

