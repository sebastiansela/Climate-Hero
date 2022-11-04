package com.example.climatehero.ViewModel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import androidx.test.espresso.core.internal.deps.guava.util.concurrent.MoreExecutors;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.climatehero.R;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.concurrent.Executor;

public class CloudVisionViewModelTest extends TestCase {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void testGetResult() {
        CloudVisionViewModel cloudVisionViewModel = new CloudVisionViewModel();
        final Executor mockExecutor = MoreExecutors.directExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());

        Resources res = appContext.getResources();
        int id = R.drawable.good_food;
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);

        cloudVisionViewModel.setImage(bitmap);

        mockExecutor.execute(() -> {
            cloudVisionViewModel.recognizeImage();
            handler.post(() -> {});
        });
        assertEquals("Food", cloudVisionViewModel.getResult().get(0));
    }
}