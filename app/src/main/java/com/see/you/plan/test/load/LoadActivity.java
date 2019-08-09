package com.see.you.plan.test.load;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.android.base.base.BaseActivity;
import com.android.base.glide.GlideRoundedCornersTransform;
import com.android.base.glide.GlideUtils;
import com.see.you.plan.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 12:25
 * desc    :
 * modify  :
 * version : 1.0
 */

public class LoadActivity extends BaseActivity {
    private static final String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565338371746&di=8803de21336b78553b4fe0ccda7cd663&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201302%2F04%2F20130204093707_dPZSV.thumb.700_0.png";
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);
        imageView5 = findViewById(R.id.image5);
        GlideUtils.loadCircleImage(this, url, imageView1);
        GlideUtils.loadImage(this, url, imageView2);
        GlideUtils.loadRoundImage(this, url, imageView3, R.mipmap.ic_launcher, 10, GlideRoundedCornersTransform.CornerType.ALL);
        GlideUtils.loadVagueImage(this, url, imageView4, R.mipmap.ic_launcher, R.mipmap.ic_launcher, 10);
    }

}
