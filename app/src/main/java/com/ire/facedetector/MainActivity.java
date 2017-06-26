package com.ire.facedetector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button processButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_face);
        processButton = (Button) findViewById(R.id.process_button);

//        Reference your image here
        final Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.tress_app);

        imageView.setImageBitmap(bitmap);

//        Makes the image fit properly
        final Paint rectPaint = new Paint();
        rectPaint.setStrokeWidth(5);
        rectPaint.setColor(Color.YELLOW);
        rectPaint.setStyle(Paint.Style.STROKE);

//        Canvas to display bitmap
        final Bitmap tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(bitmap, 0, 0, null);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setMode(FaceDetector.FAST_MODE)
                        .build();

                if (!faceDetector.isOperational()){
                    Toast.makeText(MainActivity.this, "FaceDetector couldn't be installed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<Face> sparseArray = faceDetector.detect(frame);

                for (int i = 0; i < sparseArray.size(); i++){
                    Face face = sparseArray.valueAt(i);
                    float x1 = face.getPosition().x;
                    float y1 = face.getPosition().y;
                    float x2 = x1 + face.getWidth();
                    float y2 = y1 + face.getHeight();
                    RectF rectF = new RectF(x1, y1, x2, y2);
                    canvas.drawRoundRect(rectF, 2, 2, rectPaint);
                }

                imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });
    }

}
