package mobi.mobileforce.garudamiles.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import mobi.mobileforce.garudamiles.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("SimpleDateFormat") public class CameraPage extends Fragment implements SurfaceHolder.Callback {

	private Camera camera = null;
	private SurfaceView cameraSurfaceView = null;
	private SurfaceHolder cameraSurfaceHolder = null;
	private boolean previewing = false;
	private Button btnCapture = null;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.camerapage, null);
		cameraSurfaceView = (SurfaceView) view.findViewById(R.id.surfaceView1);
		cameraSurfaceHolder = cameraSurfaceView.getHolder();
		cameraSurfaceHolder.addCallback(this);

		btnCapture = (Button) view.findViewById(R.id.button1);
		btnCapture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				camera.takePicture(cameraShutterCallback,
						cameraPictureCallbackRaw, cameraPictureCallbackJpeg);
			}
		});
		return view;
	}

	ShutterCallback cameraShutterCallback = new ShutterCallback() {
		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
		}
	};

	PictureCallback cameraPictureCallbackRaw = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
		}
	};

	PictureCallback cameraPictureCallbackJpeg = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			String output_file_name = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
					+ File.separator + timeStamp + ".jpeg";

			File pictureFile = new File(output_file_name);
			if (pictureFile.exists()) {
				pictureFile.delete();
			}

			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);

				Bitmap realImage = BitmapFactory.decodeByteArray(data, 0,
						data.length);

				ExifInterface exif = new ExifInterface(pictureFile.toString());

				Log.d("EXIF value",
						exif.getAttribute(ExifInterface.TAG_ORIENTATION));
				if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("6")) {
					realImage = RotateBitmap(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("8")) {
					realImage = RotateBitmap(realImage, 270);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("3")) {
					realImage = RotateBitmap(realImage, 180);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("0")) {
					realImage = RotateBitmap(realImage, 90);
				}

				boolean bo = realImage.compress(Bitmap.CompressFormat.JPEG,
						100, fos);

				fos.close();
				realImage.recycle();
				realImage = null;

			} catch (FileNotFoundException e) {
				Log.d("Info", "File not found: " + e.getMessage());
			} catch (IOException e) {
				Log.d("TAG", "Error accessing file: " + e.getMessage());
			}

			camera.startPreview();

			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);

			intent.setDataAndType(
					Uri.parse("file://" + pictureFile.getAbsolutePath()),
					"image/*");
			startActivity(intent);

		}
	};

	public static Bitmap RotateBitmap(Bitmap source, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
				source.getHeight(), matrix, true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			camera = Camera.open();
		} catch (RuntimeException e) {
			Log.e("TEST",
					"Device camera  is not working properly, please try after sometime.");
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}
		Camera.Parameters parameters = camera.getParameters();

		List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();

		for (int i = 0; i < previewSizes.size(); i++) {
			Log.e("camera size", "" + previewSizes.get(i).width + "x"
					+ previewSizes.get(i).height);
		}

		parameters.setPreviewSize(previewSizes.get(0).width,
				previewSizes.get(0).height);
		parameters.setPictureSize(previewSizes.get(0).width,
				previewSizes.get(0).height);
		parameters.setRotation(90);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

		int cameraId = -1;
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				Log.d("DEBUG TAG = ", "Camera found");
				cameraId = i;
				break;
			}
		}
		setCameraDisplayOrientation(getActivity(), cameraId, camera);

		previewCamera();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
	}

	public void previewCamera() {
		try {
			camera.setPreviewDisplay(cameraSurfaceHolder);
			camera.startPreview();
			previewing = true;
		} catch (Exception e) {
			Log.d("DEBUG = s", "Cannot start preview", e);
		}
	}

	public static void setCameraDisplayOrientation(Activity activity,
			int cameraId, android.hardware.Camera camera) {

		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();

		android.hardware.Camera.getCameraInfo(cameraId, info);

		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;

		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}

}
