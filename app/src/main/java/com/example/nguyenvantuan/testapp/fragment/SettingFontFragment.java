package com.example.nguyenvantuan.testapp.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.R;
import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;

@EFragment(R.layout.fragment_setting_font)
public class SettingFontFragment extends Fragment implements OnColorChangedListener{


    @ViewById(R.id.color_picker_font)
    protected LineColorPicker colorPicker;

    private String[] pallete = new String[] { "#b8c847", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a" };
    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);

        // Create palette from HEX values
        int[] colors = new int[pallete.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.parseColor(pallete[i]);
        }

        // Set palette
        colorPicker.setColors(colors);

        // Set selected color [optional]
        colorPicker.setSelectedColor(colors[0], 0);
        colorPicker.setOnColorChangedListener(this);
    }


    @Override
    public void onColorChanged(int c, int position) {
        if(position == pallete.length) {

        }
    }
}
