# FaceDetector
2nd of a series of projects I am using to learn how to implement the Google Mobile Vision API.

This is a working demo that draws yellow boxes around detected faces.

# NOTE
1. Works for only STATIC images.
2. Image/face must not be slanting.
3. Does not detect the side of a face.
4. Does not detect an image in poor lighting.
5. There are images in the drawable folder you can test with. You can also import your preferred image(s), just remember to set it on this line in the MainActivity.java class.

```
  final Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.a);
 ```

# Thanks
Tutorial source: EDMTDev.
Youtube link: http://youtube.com/eddydn71
