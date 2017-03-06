package app.com.lamdbui.android.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    // requires use of R2 as per ButterKnife README
    @BindView(R2.id.joke_text_view)
    TextView mJokeTextView;

    private String mJoke;

    public static Intent newIntent(Context packageContext, String joke) {
        Intent intent = new Intent(packageContext, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ButterKnife.bind(this);

        mJoke = getIntent().getStringExtra(EXTRA_JOKE);

        if(mJoke != null)
            mJokeTextView.setText(mJoke);
    }
}
