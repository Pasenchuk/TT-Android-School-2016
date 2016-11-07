package com.ucsoftworks.tt_android_school_2016.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;

import com.squareup.otto.Subscribe;
import com.ucsoftworks.tt_android_school_2016.R;
import com.ucsoftworks.tt_android_school_2016.events.OpenActivityStackExampleEvent;
import com.ucsoftworks.tt_android_school_2016.events.OpenFragmentStackExampleEvent;
import com.ucsoftworks.tt_android_school_2016.events.OpenNewActivityEvent;
import com.ucsoftworks.tt_android_school_2016.ui.activity_stack.ActivityStack1;
import com.ucsoftworks.tt_android_school_2016.ui.base.BaseActivity;
import com.ucsoftworks.tt_android_school_2016.ui.fragment_stack.ActivityFragmentStack;
import com.ucsoftworks.tt_android_school_2016.ui.new_activity.ActivityNew;

public class MainActivity extends BaseActivity implements MainFragment.MainFragmentCallbacks {

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();

        if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            // TODO: 07/11/2016 в этом интенте можно найти ссылку на картинку 

        }
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


    //Сделаем реализацию методов коллбэка фрагмента:

    @Override
    public void onNewActivityButtonPressed() {
        startActivity(new Intent(this, ActivityNew.class));
    }

    @Override
    public void onActivityStackExampleButtonPressed() {
        startActivity(new Intent(this, ActivityStack1.class));
    }

    @Override
    public void onFragmentStackExampleButtonPressed() {
        startActivity(new Intent(this, ActivityFragmentStack.class));
    }

//    Аналогично можно использовать шину. Разница - Активити не обязана ничего имплементить,
// а фрагменту не надо проверять, имплементит ли активити его коллбэки

    @Subscribe
    public void onOpenNewActivityEvent(OpenNewActivityEvent event) {
        startActivity(new Intent(this, ActivityNew.class));
    }
    @Subscribe
    public void onOpenActivityStackExampleEvent(OpenActivityStackExampleEvent event) {
        startActivity(new Intent(this, ActivityStack1.class));
    }
    @Subscribe
    public void onOpenFragmentStackExampleEvent(OpenFragmentStackExampleEvent event) {
        startActivity(new Intent(this, ActivityFragmentStack.class));
    }

}

