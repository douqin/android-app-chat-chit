package com.douqin.chatchitVN.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class ImageUtil {
    private Bitmap bitmap;
    public int l, r;
    private final boolean isImgVertical;

    public Bitmap getBitmap() {
        return bitmap;
    }

    private ImageUtil(Bitmap bitmap) {
        this.bitmap = bitmap;
        isImgVertical = this.bitmap.getHeight() > this.bitmap.getWidth();
        setScaleImg(this.bitmap.getHeight(), this.bitmap.getWidth());
        this.convertSize();
    }

    public static ImageUtil buider(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new ImageUtil(bitmap);
    }

    @NonNull
    public String toString() {
        return this.convert(this.bitmap);
    }

    public static Bitmap convert(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.NO_WRAP
        );
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private String convert(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        bitmap = decodeSampledBitmapFromResource(byteArray, this.r, this.l);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private void convertSize() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        bitmap = decodeSampledBitmapFromResource(byteArray, this.r, this.l);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
    }

    private void setScaleImg(int height, int width) {
        if (isImgVertical) {
            while (height > MotherCanvas.height / 3 || width > MotherCanvas.width / 3) {
                height /= 1.1;
                width /= 1.1;
            }
        } else {
            while (width > MotherCanvas.height / 3 || height > MotherCanvas.width / 3) {
                height /= 1.1;
                width /= 1.1;
            }
        }
        this.l = height;
        this.r = width;
    }

    private int calculateInSampleSize(@NonNull BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

// Calculate the largest inSampleSize value that is a power of 2 and keeps both
// height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private Bitmap decodeSampledBitmapFromResource(byte[] image, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true
        // to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(image, 0, image.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(image, 0, image.length, options);
    }
}