package what.is.jsonpractice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImageExpand extends Fragment {
    Bundle urlBundle;
    Unbinder unbinder;
    @BindView(R.id.iv_expand)
    ImageView urlExpandImage;
    String url;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        urlBundle = this.getArguments();
        if (urlBundle != null) {
            url = urlBundle.getString("URL", "");
        }
        View v = inflater.inflate(R.layout.iv_expand, container, false);
        unbinder = ButterKnife.bind(this, v);
        Glide.with(this).load(url).into(urlExpandImage);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
