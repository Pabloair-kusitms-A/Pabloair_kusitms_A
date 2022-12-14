package com.example.pabloair_kusitms_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserCheckActivity extends AppCompatActivity {

    String UserType = "";
    DBManager DB;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check);

        // 더미데이터 생성을 위한 DB 호출용
        DB = new DBManager(this);

        // DB 테스트
        // add할 때 key-value 형태로 넘겨주어야 함!
        Map<String, Object> user = new HashMap<>();
        user.put("id", "test");
        user.put("phone", "01012345678");
        user.put("pwd", "1234567");

        db.collection("User").add(user);



        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        ImageButton ManagerBtn = findViewById(R.id.user_check_manager_btn);
        ImageButton UserBtn = findViewById(R.id.user_check_user_btn);
        LinearLayout BottomLayout = findViewById(R.id.user_check_tv_layoutA);
        Button NextBtn = findViewById(R.id.user_check_next_btn);
        NextBtn.setVisibility(View.INVISIBLE);

        View.OnClickListener layoutVisible = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == UserBtn) {
                    UserBtn.setSelected(true);
                    UserBtn.setPressed(true);
                    ManagerBtn.setSelected(false);
                    ManagerBtn.setSelected(false);

                    BottomLayout.setVisibility(View.INVISIBLE);
                    NextBtn.setVisibility(View.VISIBLE);
                    UserType = "사용자";
                    Log.d("UserType", UserType);

                } else if(v == ManagerBtn) {
                    UserBtn.setSelected(false);
                    UserBtn.setPressed(false);
                    ManagerBtn.setSelected(true);
                    ManagerBtn.setSelected(true);

                    BottomLayout.setVisibility(View.INVISIBLE);
                    NextBtn.setVisibility(View.VISIBLE);
                    UserType = "관리자";
                    Log.d("UserType", UserType);
                }
            }
        };

        UserBtn.setOnClickListener(layoutVisible);
        ManagerBtn.setOnClickListener(layoutVisible);


        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserType.equals("사용자")) {

                    startActivity(new Intent(getApplication(), User_Login_Activity.class));

                } else if (UserType.equals("관리자")) {

                    startActivity(new Intent(getApplication(), Manager_home_Activity.class));
                }

            }
        });

        TextView signupText = (TextView) findViewById(R.id.user_check_sign_in_tv);
        signupText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Signup_Activity.class);
                startActivity(intent);
            }
        });

    }

}