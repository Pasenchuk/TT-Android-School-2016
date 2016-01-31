package com.ucsoftworks.tt_android_school_2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        вызываем getSupportFragmentManager, так как по умлочанию у нас используется
//                библиотека AppCompat, обеспечивающая совместимость фишек новых андроидов
//                со старыми
        if (savedInstanceState == null)
//            если Активити создаётся в первый раз, то не будет сохранённого состояния,
//                следовательно нужно добавить фрагмент в контейнер
//                Если Активити создаётся не в первый раз, например, если она ранее была разрушена
//        из за нехватки памяти, то добавленный фрагмент восстановится автоматически
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
    }
}
