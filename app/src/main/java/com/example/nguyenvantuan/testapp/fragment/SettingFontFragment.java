package com.example.nguyenvantuan.testapp.fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.RadioButton;
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


    @ViewById(R.id.rb_font_type)
    protected RadioButton rbType;

    @ViewById(R.id.rb_font_type1)
    protected RadioButton rbType1;

    @ViewById(R.id.rb_font_type2)
    protected RadioButton rbType2;

    @ViewById(R.id.rb_font_type3)
    protected RadioButton rbType3;


    @ViewById(R.id.color_picker_font)
    protected LineColorPicker colorPicker;

    private String[] pallete = new String[] { "#b8c847", "#67bb43", "#41b691",
            "#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a" };


    @AfterViews
    public void init() {
        Typeface typeFace;
        getActivity().setTitle(R.string.title_activity_pass_code);

        typeFace = Typeface.createFromAsset(getResources().getAssets(), "font/alext_bust.ttf");
        rbType.setTypeface(typeFace);

        typeFace = Typeface.createFromAsset(getResources().getAssets(), "font/black_jack.ttf");
        rbType1.setTypeface(typeFace);

        typeFace = Typeface.createFromAsset(getResources().getAssets(), "font/good_dog.otf");
        rbType2.setTypeface(typeFace);

        typeFace = Typeface.createFromAsset(getResources().getAssets(), "font/great_vipe.otf");
        rbType3.setTypeface(typeFace);

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
