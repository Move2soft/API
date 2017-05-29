package com.wanttomeet.uv.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.wanttomeet.uv.Listener.ResponseListener;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.adapter.SelectImageAdapter;
import com.wanttomeet.uv.model.Profile;
import com.wanttomeet.uv.utils.AsyncHttpRequest;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.Constants;
import com.wanttomeet.uv.utils.Debug;
import com.wanttomeet.uv.utils.RequestParamsUtils;
import com.wanttomeet.uv.utils.ResponseHandler;
import com.wanttomeet.uv.utils.URLs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewProfile extends BaseActivity implements ResponseListener {



    int on = 1;
    int likeimafe = 1;

    @Bind(R.id.ivProfile)
    ImageView ivProfile;


    @Bind(R.id.tvEmailId)
    TextView tvEmailId;

    @Bind(R.id.tvMobileNo)
    TextView tvMobileNo;

    @Bind(R.id.tvDob)
    TextView tvDob;

    @Bind(R.id.tvGender)
    TextView tvGender;

    @Bind(R.id.tvPersonalInfoSave)
    TextView tvPersonalInfoSave;

    @Bind(R.id.tvCity)
    TextView tvCity;

    @Bind(R.id.iv_share)
    ImageView iv_share;

    @Bind(R.id.tvstate)
    TextView tvstate;

    @Bind(R.id.tvNameProfile)
    TextView tvNameProfile;

    @Bind(R.id.tvHighestEduction)
    TextView tvHighestEduction;

    @Bind(R.id.tvCollage)
    TextView tvCollage;

    @Bind(R.id.tvEmployee)
    TextView tvEmployee;

    @Bind(R.id.tvOccuption)
    TextView tvOccuption;

    @Bind(R.id.tvOccuptionDetail)
    TextView tvOccuptionDetail;

    @Bind(R.id.tvProfessionalInfoSave)
    TextView tvProfessionalInfoSave;

    @Bind(R.id.tvCOuntry)
    TextView tvCOuntry;

    @Bind(R.id.tvState)
    TextView tvState;

    @Bind(R.id.tvCIty)
    TextView tvCIty;

    @Bind(R.id.tvEditPersonalInfo)
    TextView tvEditPersonalInfo;

    @Bind(R.id.tvEditABoutMe)
    TextView tvEditABoutMe;

    @Bind(R.id.tvEditProfessionalnfo)
    TextView tvEditProfessionalnfo;

    @Bind(R.id.tvName)
    TextView tvName;


    @Bind(R.id.llIsAccess)
    LinearLayout llIsAccess;

    @Bind(R.id.tvAboutMe)
    TextView tvAboutMe;

    @Bind(R.id.tvABoutMeSave)
    TextView tvABoutMeSave;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etEmailId)
    EditText etEmailId;

    @Bind(R.id.etMobileNo)
    EditText etMobileNo;

    @Bind(R.id.etGender)
    EditText etGender;

    @Bind(R.id.etdob)
    EditText etdob;

    @Bind(R.id.etHigherEduction)
    EditText etHigherEduction;

    @Bind(R.id.etCollage)
    EditText etCollage;

    @Bind(R.id.etEmployeeIn)
    EditText etEmployeeIn;

    @Bind(R.id.etOccupation)
    EditText etOccupation;

    @Bind(R.id.etOccuptionDetail)
    EditText etOccuptionDetail;

    @Bind(R.id.iv_like)
    ImageView iv_like;

    @Bind(R.id.iv_call)
    ImageView iv_call;

    @Bind(R.id.etCountry)
    EditText etCountry;

    @Bind(R.id.etState)
    EditText etState;

    @Bind(R.id.etCity)
    EditText etCity;

    @Bind(R.id.etAboutMe)
    EditText etAboutMe;

    @Bind(R.id.toggleButton)
    ToggleButton toggleButton;

    private String method;
    public AlertDialog alertDialog;
    int REQUEST_CAMERA = 541;
    int SELECT_IMAGE;
    private String url;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        ButterKnife.bind(this);
        bindToolbar();
        showEmptyOnly();
        setTitle("Profile Infrormation");
        showBack();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            setMemberData();
        } else {
            setData();
        }

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("android.intent.action.SEND");
                i.setType("text/plain");
                i.putExtra("android.intent.extra.SUBJECT", "Vaishnava Guide");
                i.putExtra("android.intent.extra.TEXT", "Download Vaishnava Guide Now To Get information and guideline to follow vaishnavism.\n\nDownload From :\nplay.google.com/store/apps/details?id=" + ViewProfile.this.getPackageName());
                ViewProfile.this.startActivity(Intent.createChooser(i, "Share via"));
            }
        });

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobileno = bundle.getString(RequestParamsUtils.MDOB);

                if (mobileno.equals("")) {
                    Toast.makeText(ViewProfile.this, "No Contact Number Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewProfile.this, "Call Now...", Toast.LENGTH_SHORT).show();
                    String no = mobileno.toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + no));
                    if (ActivityCompat.checkSelfPermission(ViewProfile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        // ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        // public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        // int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
            }
        });


    }

    public void setMemberData() {
        tvName.setText(bundle.getString(RequestParamsUtils.MNAME));
        tvNameProfile.setText(bundle.getString(RequestParamsUtils.MNAME));
        tvEmailId.setText(bundle.getString(RequestParamsUtils.MEMAIL));
        tvMobileNo.setText(bundle.getString(RequestParamsUtils.MCONTACT));
        tvGender.setText(bundle.getString(RequestParamsUtils.MGENDER));
        tvDob.setText(bundle.getString(RequestParamsUtils.MDOB));
        tvHighestEduction.setText(bundle.getString(RequestParamsUtils.MEDUCTION));
        tvCollage.setText(bundle.getString(RequestParamsUtils.MCOLLAGE));
        tvEmployee.setText(bundle.getString(RequestParamsUtils.MEMPLOUEDIN));
        tvOccuption.setText(bundle.getString(RequestParamsUtils.MOCCUPTION));
        tvOccuptionDetail.setText(bundle.getString(RequestParamsUtils.MOCCUPTIONDETAIL));
        tvCIty.setText(bundle.getString(RequestParamsUtils.MCITY));
        tvCity.setText(bundle.getString(RequestParamsUtils.MCITY));
        tvState.setText(bundle.getString(RequestParamsUtils.MSTATE));
        tvstate.setText(bundle.getString(RequestParamsUtils.MSTATE));
        tvCOuntry.setText(bundle.getString(RequestParamsUtils.MCOUNTRY));
        tvAboutMe.setText(bundle.getString(RequestParamsUtils.MABOUT));
        Picasso.with(this).load(new URLs().IMAGE + bundle.getString(RequestParamsUtils.MIMAGE)).into(ivProfile);
        tvEditABoutMe.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tvEditPersonalInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tvEditProfessionalnfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        llIsAccess.setVisibility(View.GONE);
        ivProfile.setClickable(false);
    }


    public void setData() {
            tvName.setText(Constants.PROFILE.result.get(0).mName);
            tvNameProfile.setText(Constants.PROFILE.result.get(0).mName);
            tvEmailId.setText(Constants.PROFILE.result.get(0).mEmail);
            tvMobileNo.setText(Constants.PROFILE.result.get(0).mContact);
            tvGender.setText(Constants.PROFILE.result.get(0).mGender);
            tvDob.setText(Constants.PROFILE.result.get(0).mDob);
            tvHighestEduction.setText(Constants.PROFILE.result.get(0).mEducation);
            tvCollage.setText(Constants.PROFILE.result.get(0).mCollage);
            tvEmployee.setText(Constants.PROFILE.result.get(0).mEmployedin);
            tvOccuption.setText(Constants.PROFILE.result.get(0).mOccupation);
            tvOccuptionDetail.setText(Constants.PROFILE.result.get(0).mOccupationDetail);
            tvCIty.setText(Constants.PROFILE.result.get(0).mCity);
            tvCity.setText(Constants.PROFILE.result.get(0).mCity);
            tvState.setText(Constants.PROFILE.result.get(0).mState);
            tvstate.setText(Constants.PROFILE.result.get(0).mState);
            tvCOuntry.setText(Constants.PROFILE.result.get(0).mContry);
            tvAboutMe.setText(Constants.PROFILE.result.get(0).mAbout);
            etName.setText(Constants.PROFILE.result.get(0).mName);
            etEmailId.setText(Constants.PROFILE.result.get(0).mEmail);
            etMobileNo.setText(Constants.PROFILE.result.get(0).mContact);
            etGender.setText(Constants.PROFILE.result.get(0).mGender);
            etdob.setText(Constants.PROFILE.result.get(0).mDob);
            etHigherEduction.setText(Constants.PROFILE.result.get(0).mEducation);
            etCollage.setText(Constants.PROFILE.result.get(0).mCollage);
            etEmployeeIn.setText(Constants.PROFILE.result.get(0).mEmployedin);
            etOccupation.setText(Constants.PROFILE.result.get(0).mOccupation);
            etOccuptionDetail.setText(Constants.PROFILE.result.get(0).mOccupationDetail);
            etCity.setText(Constants.PROFILE.result.get(0).mCity);
            etState.setText(Constants.PROFILE.result.get(0).mState);
            etCountry.setText(Constants.PROFILE.result.get(0).mContry);
            etAboutMe.setText(Constants.PROFILE.result.get(0).mAbout);
            Picasso.with(this).load(new URLs().IMAGE + Constants.PROFILE.result.get(0).mImage).into(ivProfile);

    }


    @OnClick(R.id.toggleButton)
    public void toggleButton() {
        if (on == 0) {
            toggleButton.setBackgroundResource(R.drawable.btn_bac);
            on = 1;
        } else if (on == 1) {
            toggleButton.setBackgroundResource(R.drawable.btn_shape_off);
            Toast.makeText(ViewProfile.this, "off", Toast.LENGTH_SHORT).show();
            on = 0;
        } else {

        }
    }

    @OnClick(R.id.iv_like)
    public void iv_like() {
        Toast.makeText(ViewProfile.this, "Like", Toast.LENGTH_SHORT).show();
        if (likeimafe == 0) {
            iv_like.setImageResource(R.drawable.ic_likefill);
            likeimafe = 1;
        } else if (likeimafe == 1) {
            iv_like.setImageResource(R.drawable.ic_like1);
            likeimafe = 0;
        } else {

        }
    }

    @OnClick(R.id.tvEditPersonalInfo)
    public void tvEditPersonalInfoClick() {
        EditPersonalInfo();
    }

    public void EditPersonalInfo() {
        etName.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.GONE);
        etEmailId.setVisibility(View.VISIBLE);
        tvEmailId.setVisibility(View.GONE);
        etMobileNo.setVisibility(View.VISIBLE);
        tvMobileNo.setVisibility(View.GONE);
        etGender.setVisibility(View.VISIBLE);
        tvGender.setVisibility(View.GONE);
        etdob.setVisibility(View.VISIBLE);
        tvDob.setVisibility(View.GONE);
        tvPersonalInfoSave.setVisibility(View.VISIBLE);
    }

    public void showPersonalInfo() {
        etName.setVisibility(View.GONE);
        tvName.setVisibility(View.VISIBLE);
        etEmailId.setVisibility(View.GONE);
        tvEmailId.setVisibility(View.VISIBLE);
        etMobileNo.setVisibility(View.GONE);
        tvMobileNo.setVisibility(View.VISIBLE);
        etGender.setVisibility(View.GONE);
        tvGender.setVisibility(View.VISIBLE);
        etdob.setVisibility(View.GONE);
        tvDob.setVisibility(View.VISIBLE);
        tvPersonalInfoSave.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvEditProfessionalnfo)
    public void tvEditProfessionalInfoClick() {
        EditProfessionalnfo();
    }

    public void EditProfessionalnfo() {
        etHigherEduction.setVisibility(View.VISIBLE);
        tvHighestEduction.setVisibility(View.GONE);
        etCollage.setVisibility(View.VISIBLE);
        tvCollage.setVisibility(View.GONE);
        etEmployeeIn.setVisibility(View.VISIBLE);
        tvEmployee.setVisibility(View.GONE);
        etOccupation.setVisibility(View.VISIBLE);
        tvOccuption.setVisibility(View.GONE);
        etOccuptionDetail.setVisibility(View.VISIBLE);
        tvOccuptionDetail.setVisibility(View.GONE);
        tvProfessionalInfoSave.setVisibility(View.VISIBLE);
    }

    public void showProfessionalInfo() {
        etHigherEduction.setVisibility(View.GONE);
        tvHighestEduction.setVisibility(View.VISIBLE);
        etCollage.setVisibility(View.GONE);
        tvCollage.setVisibility(View.VISIBLE);
        etEmployeeIn.setVisibility(View.GONE);
        tvEmployee.setVisibility(View.VISIBLE);
        etOccupation.setVisibility(View.GONE);
        tvOccuption.setVisibility(View.VISIBLE);
        etOccuptionDetail.setVisibility(View.GONE);
        tvOccuptionDetail.setVisibility(View.VISIBLE);
        tvProfessionalInfoSave.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvEditABoutMe)
    public void tvEditABoutMeClick() {
        EditAboutMeInfo();
    }

    public void EditAboutMeInfo() {
        etAboutMe.setVisibility(View.VISIBLE);
        tvAboutMe.setVisibility(View.GONE);
        tvABoutMeSave.setVisibility(View.VISIBLE);
    }

    public void showAboutMeInfo() {
        etAboutMe.setVisibility(View.GONE);
        tvAboutMe.setVisibility(View.VISIBLE);
        tvABoutMeSave.setVisibility(View.GONE);
    }


    @OnClick(R.id.tvPersonalInfoSave)
    public void tvPersonalInfoSave() {
        editProfile(etName.getText().toString(), etGender.getText().toString(), etMobileNo.getText().toString()
                , etdob.getText().toString(), etEmailId.getText().toString(), Constants.PROFILE.result.get(0).mCity
                , Constants.PROFILE.result.get(0).mState, Constants.PROFILE.result.get(0).mContry,
                Constants.PROFILE.result.get(0).mEducation, Constants.PROFILE.result.get(0).mCollage,
                Constants.PROFILE.result.get(0).mEmployedin, Constants.PROFILE.result.get(0).mOccupation,
                Constants.PROFILE.result.get(0).mOccupationDetail, Constants.PROFILE.result.get(0).mAbout, Constants.PROFILE.result.get(0).mImage, true, "EditPersonalInfo");
    }

    @OnClick(R.id.tvProfessionalInfoSave)
    public void tvProfessionalInfoSave() {
        editProfile(Constants.PROFILE.result.get(0).mName, Constants.PROFILE.result.get(0).mGender, Constants.PROFILE.result.get(0).mContact
                , Constants.PROFILE.result.get(0).mDob, Constants.PROFILE.result.get(0).mEmail, Constants.PROFILE.result.get(0).mCity
                , Constants.PROFILE.result.get(0).mState, Constants.PROFILE.result.get(0).mContry,
                etHigherEduction.getText().toString(), etCollage.getText().toString(),
                etEmployeeIn.getText().toString(), etOccupation.getText().toString(),
                etOccuptionDetail.getText().toString(), Constants.PROFILE.result.get(0).mAbout, Constants.PROFILE.result.get(0).mImage, true, "EditProfessionalInfo");
    }

    @OnClick(R.id.tvABoutMeSave)
    public void tvABoutMeSaveClick() {
        editProfile(Constants.PROFILE.result.get(0).mName, Constants.PROFILE.result.get(0).mGender, Constants.PROFILE.result.get(0).mContact
                , Constants.PROFILE.result.get(0).mDob, Constants.PROFILE.result.get(0).mEmail, Constants.PROFILE.result.get(0).mCity
                , Constants.PROFILE.result.get(0).mState, Constants.PROFILE.result.get(0).mContry,
                Constants.PROFILE.result.get(0).mEducation, Constants.PROFILE.result.get(0).mCollage,
                Constants.PROFILE.result.get(0).mEmployedin, Constants.PROFILE.result.get(0).mOccupation,
                Constants.PROFILE.result.get(0).mOccupationDetail, etAboutMe.getText().toString(), Constants.PROFILE.result.get(0).mImage, true, "EditAboutMe");
    }

    @OnClick(R.id.ivProfile)
    public void addImageClick() {
        selectImage(Arrays.asList("Take Photo", "Choose from Gallery", "Cancel"));

    }


    private void selectImage(final List<String> list) {


        LayoutInflater li = LayoutInflater.from(this);

        final View confirmDialog = li.inflate(R.layout.category_dialog, null);

        TextView tv_dialog_title = (TextView) confirmDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Image");

        final ListView listview_category_list = (ListView) confirmDialog.findViewById(R.id.listview_category_list);
        SelectImageAdapter spinnerAdapter = new SelectImageAdapter(this);
        spinnerAdapter.addAll(list);
        listview_category_list.setAdapter(spinnerAdapter);

        listview_category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).equals("Take Photo")) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (list.get(i).equals("Choose from Gallery")) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_IMAGE);
                } else if (list.get(i).equals("Cancel")) {
                    alertDialog.dismiss();
                }
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.etEmployeeIn)
    public void etEmployeeInClick() {
        selectEmployeedIn(Arrays.asList("Government", "Private", "Business", "Self Employed", "Not Working"));

    }

    private void selectEmployeedIn(final List<String> list) {


        final LayoutInflater li = LayoutInflater.from(this);

        final View confirmDialog = li.inflate(R.layout.category_dialog, null);

        TextView tv_dialog_title = (TextView) confirmDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Employeed in");

        final ListView listview_category_list = (ListView) confirmDialog.findViewById(R.id.listview_category_list);
        SelectImageAdapter spinnerAdapter = new SelectImageAdapter(this);
        spinnerAdapter.addAll(list);
        listview_category_list.setAdapter(spinnerAdapter);

        listview_category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                etEmployeeIn.setText(list.get(i));
                alertDialog.dismiss();
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.etGender)
    public void etGenderCLick() {
        etGenderCLick(Arrays.asList("Male", "Female"));

    }

    private void etGenderCLick(final List<String> list) {


        final LayoutInflater li = LayoutInflater.from(this);

        final View confirmDialog = li.inflate(R.layout.category_dialog, null);

        TextView tv_dialog_title = (TextView) confirmDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Gender");

        final ListView listview_category_list = (ListView) confirmDialog.findViewById(R.id.listview_category_list);
        SelectImageAdapter spinnerAdapter = new SelectImageAdapter(this);
        spinnerAdapter.addAll(list);
        listview_category_list.setAdapter(spinnerAdapter);

        listview_category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                etGender.setText(list.get(i));
                alertDialog.dismiss();
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        url = getStringImage(thumbnail);
        Log.e("base url is:", url);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivProfile.setImageBitmap(thumbnail);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);
        ivProfile.setImageBitmap(bm);
        url = getStringImage(bm);
        Log.e("base url is:", url);
        editProfile(Constants.PROFILE.result.get(0).mName, Constants.PROFILE.result.get(0).mGender, Constants.PROFILE.result.get(0).mContact
                , Constants.PROFILE.result.get(0).mDob, Constants.PROFILE.result.get(0).mEmail, Constants.PROFILE.result.get(0).mCity
                , Constants.PROFILE.result.get(0).mState, Constants.PROFILE.result.get(0).mContry,
                Constants.PROFILE.result.get(0).mEducation, Constants.PROFILE.result.get(0).mCollage,
                Constants.PROFILE.result.get(0).mEmployedin, Constants.PROFILE.result.get(0).mOccupation,
                Constants.PROFILE.result.get(0).mOccupationDetail, Constants.PROFILE.result.get(0).mAbout, url, true, "image");
    }

    public String getStringImage(Bitmap bmp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void editProfile(String name, String gender, String mobileno, String dob, String email,
                             String city, String state, String coutry, String eduction, String collage,
                             String employeedin, String occuption, String occuptiondetail, String about, String image, boolean isphoneallow, String method) {
        try {
            this.method = method;
            showProgress("");
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.MID, Constants.PROFILE.result.get(0).mId);
            params.put(RequestParamsUtils.MID, "");
            params.put(RequestParamsUtils.MNAME, name);
            params.put(RequestParamsUtils.MGENDER, gender);
            params.put(RequestParamsUtils.MCONTACT, mobileno);
            params.put(RequestParamsUtils.MDOB, dob);
            params.put(RequestParamsUtils.MPASSWORD, "");
            params.put(RequestParamsUtils.MEMAIL, email);
            params.put(RequestParamsUtils.MCITY, city);
            params.put(RequestParamsUtils.MSTATE, state);
            params.put(RequestParamsUtils.MCOUNTRY, coutry);
            params.put(RequestParamsUtils.MEDUCTION, eduction);
            params.put(RequestParamsUtils.MCOLLAGE, collage);
            params.put(RequestParamsUtils.MEMPLOUEDIN, employeedin);
            params.put(RequestParamsUtils.MOCCUPTION, occuption);
            params.put(RequestParamsUtils.MOCCUPTIONDETAIL, occuptiondetail);
            params.put(RequestParamsUtils.MABOUT, about);
            params.put(RequestParamsUtils.MTOKEN, "");
            params.put(RequestParamsUtils.MLAT, "");
            params.put(RequestParamsUtils.MLONG, "");
            params.put(RequestParamsUtils.MIMAGE, image);
            params.put(RequestParamsUtils.MIsPhoneAllow, isphoneallow);

            Log.e("editProfile", image);

            Debug.e("doRegistration", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

            clientPhotos.post(new URLs().REGISTER, params, new ResponseHandler(ViewProfile.this, this));

        } catch (Exception e) {
            Debug.e("doRegistration Exception", e.getMessage());
        }
    }


    @Override
    public void onResponse(String response) {
        dismissProgress();
        if (response != null && response.length() > 0) {
            try {
                dismissProgress();
                Log.e("doRegistration", response);

                if (response != null && response.length() > 0) {

                    Profile pt = new Gson().fromJson(
                            response, new TypeToken<Profile>() {
                            }.getType());

                    if (pt.st == 1) {
                        Constants.PROFILE = pt;
                        SharedPreferences.Editor editor = getPreferences().edit();
                        editor.putString(RequestParamsUtils.USERPROFILE, response);
                        editor.commit();
                        setData();
                        if (method.equals("EditPersonalInfo")) {
                            showPersonalInfo();
                        } else if (method.equals("EditProfessionalInfo")) {
                            showProfessionalInfo();
                        } else if (method.equals("EditAboutMe")) {
                            showAboutMeInfo();
                        } else if (method.equals("image")) {
                            Picasso.with(this).load(new URLs().IMAGE + Constants.PROFILE.result.get(0).mImage).into(ivProfile);
                        }

                    } else {
                        Toast.makeText(getActivity(), "Invild Data !...Try Again...!", Toast.LENGTH_LONG).show();

                    }

                }

            } catch (Exception e) {
                Debug.e("RetryHandler Sucess Exception", e.getMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
