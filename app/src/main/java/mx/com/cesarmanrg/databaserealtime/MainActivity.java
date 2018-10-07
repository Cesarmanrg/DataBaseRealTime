package mx.com.cesarmanrg.databaserealtime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_START = "start";
    private static final String PATH_MESSAGE = "message";
    TextView tvMessage;
    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.btSend)
    Button btSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvMessage = findViewById(R.id.tvMessage);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvMessage.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "No se recibe valores de Firebase", Toast.LENGTH_LONG).show();

            }
        });


    }

    @OnClick(R.id.btSend)
    public void onViewClicked() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        reference.setValue(etMessage.getText().toString().trim());
        etMessage.setText("SettingsSettings");
    }
}
