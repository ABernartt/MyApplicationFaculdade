package bernartt.faculdade.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

import bernartt.faculdade.com.myapplication.implementacaoDois.ImplementacaoDoisActivity;
import bernartt.faculdade.com.myapplication.implementacaoUm.ImplementacaoUmActivity;


public class MainActivity extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Button mImplUmBtn, mImplDoisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImplUmBtn = (Button) findViewById(R.id.implUm);
        mImplDoisBtn = (Button) findViewById(R.id.implDois);
        mImplUmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ImplementacaoUmActivity.class);
                startActivity(i);
                finish();
            }
        });

        mImplDoisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ImplementacaoDoisActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
