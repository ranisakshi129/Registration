package com.viw.registration
import android.Manifest

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.hbb20.CountryCodePicker
import de.hdodenhof.circleimageview.CircleImageView

import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImageIv: CircleImageView
    private lateinit var cameraIconIv: ImageView
    private var photoUri: Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_PICK = 2
    private val CAMERA_PERMISSION_REQUEST = 100
    private var selectedDob: String? = null
    private var currentPhotoPath: String? = null
    private lateinit var ccp: CountryCodePicker
    private lateinit var phoneEdt: EditText
    private lateinit var phoneErrorText: TextView
    private lateinit var emergencyccp: CountryCodePicker
    private lateinit var emergencyPhoneEdt: EditText
    private lateinit var emergencyphoneErrorText: TextView
    private lateinit var genderErrorText: TextView
    private lateinit var dobErrorText: TextView
    private lateinit var bloodErrorText: TextView
    private lateinit var statusErrorText: TextView
    private lateinit var heightErrorText: TextView
    private lateinit var weightErrorText: TextView
    private lateinit var locationErrorText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        cameraIconIv = findViewById(R.id.cameraIconIv)
        profileImageIv = findViewById(R.id.profileImageIv)
        profileImageIv = findViewById(R.id.profileImageIv)

        cameraIconIv.setOnClickListener {
            showImageOptions()
        }

        val locationEdt = findViewById<EditText>(R.id.locationEdt)
        locationEdt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS


        var nextBtn: Button = findViewById(R.id.nextBtn)
        val arrowIv: ImageView = findViewById(R.id.arrowIv)
        var nameEdt: EditText = findViewById(R.id.nameEdt)
        nameEdt.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        var nameEdt2: TextView = findViewById(R.id.name3)
        var phoneEdt2: TextView = findViewById(R.id.phoneErrorText)
        var emailEdt: EditText = findViewById(R.id.emailEdt)
        emailEdt.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        var emailEdt2: TextView = findViewById(R.id.email3)
        ccp = findViewById(R.id.countryCodePicker)
        phoneEdt = findViewById(R.id.phoneEdt)
        phoneEdt.inputType = InputType.TYPE_CLASS_PHONE
        phoneErrorText = findViewById(R.id.phoneErrorText)
        emergencyccp = findViewById(R.id.emergencyCountryCodePicker)
        emergencyPhoneEdt = findViewById(R.id.emergencyPhoneEdt)
        emergencyphoneErrorText = findViewById(R.id.emergencyPhoneErrorText)
        genderErrorText = findViewById(R.id.genderErrorText)
        dobErrorText = findViewById(R.id.dobErrorText)
        bloodErrorText = findViewById(R.id.bloodGroupErrorText)
        statusErrorText = findViewById(R.id.statusErrorText)
        heightErrorText = findViewById(R.id.heightErrorText)
        weightErrorText = findViewById(R.id.weightErrorText)
        locationErrorText = findViewById(R.id.locationErrorText)


        arrowIv.setOnClickListener() {
            finish()
        }

        val dobLayout = findViewById<LinearLayout>(R.id.dobLayout)
        val dobTextView = findViewById<TextView>(R.id.dobTextView)

        dobLayout.setOnClickListener {
            showDatePicker(dobTextView, dobErrorText)
        }

        nameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name = s.toString().trim()

                val capitalizedName = capitalizeWords(name)
                if (capitalizedName != name) {
                    nameEdt.removeTextChangedListener(this) // Prevents infinite loop
                    nameEdt.setText(capitalizedName)
                    nameEdt.setSelection(capitalizedName.length) // Moves cursor to end
                    nameEdt.addTextChangedListener(this)
                }

                val errorMessage = isValidName(capitalizedName)
                if (errorMessage != null) {
                    nameEdt2.visibility = View.VISIBLE
                    nameEdt2.text = errorMessage
                } else {
                    nameEdt2.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()

                if (email.isEmpty()) {
                    emailEdt2.visibility = View.VISIBLE
                    emailEdt2.text = "Please enter your email."
                } else {
                    emailEdt2.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        phoneEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val phone = s.toString().trim()

                if (phone.length > 10) {
                    phoneEdt.removeTextChangedListener(this)
                    phoneEdt.setText(phone.substring(0, 10))  // Trim extra digits
                    phoneEdt.setSelection(10)  // Move cursor to end
                    phoneEdt.addTextChangedListener(this)
                    return
                }

                if (phone.isEmpty()) {
                    phoneEdt2.visibility = View.VISIBLE // No error while empty
                    phoneEdt2.text = "Please enter your phone number."
                } else if (phone.length == 1) {
                    phoneEdt2.visibility = View.VISIBLE
                    phoneEdt2.text = "Please enter 10-digit number."
                } else {
                    phoneEdt2.visibility = View.GONE  // Hide error when 10 digits are entered
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        emergencyPhoneEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val emergencyPhone = s.toString().trim()

                if (emergencyPhone.length > 10) {
                    emergencyPhoneEdt.removeTextChangedListener(this)
                    emergencyPhoneEdt.setText(emergencyPhone.substring(0, 10))  // Trim extra digits
                    emergencyPhoneEdt.setSelection(10)  // Move cursor to end
                    emergencyPhoneEdt.addTextChangedListener(this)
                    return
                }
                if (emergencyPhone.isEmpty()) {
                    emergencyphoneErrorText.visibility = View.VISIBLE  // No error while empty
                    emergencyphoneErrorText.text = "Please enter your phone number."
                } else if (emergencyPhone.length == 1) {
                    emergencyphoneErrorText.visibility = View.VISIBLE
                    emergencyphoneErrorText.text = "Please enter 10 digit number."
                } else {
                    emergencyphoneErrorText.visibility =
                        View.GONE  // Hide error when 10 digits are entered
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        val genderOptions = listOf("Select Gender", "Male", "Female", "Others")

        val maritalStatusOptions =
            listOf("Select Marital Status", "Single", "Married", "Divorced", "Widowed")

        val bloodOptions = listOf(
            "Select Blood Group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        )

        val heightOptions = listOf(
            "Select Height", "4'0\"", "4'1\"", "4'2\"", "4'3\"", "4'4\"", "4'5\"",
            "4'6\"", "4'7\"", "4'8\"", "4'9\"", "5'0\"", "5'1\"", "5'2\"", "5'3\"",
            "5'4\"", "5'5\"", "5'6\"", "5'7\"", "5'8\"", "5'9\"", "6'0\"", "6'1\"", "6'2\""
        )
        val weightOptions = listOf(
            "Select Weight", "40 kg", "45 kg", "50 kg", "55 kg",
            "60 kg", "65 kg", "70 kg", "75 kg", "80 kg", "85 kg", "90 kg", "95 kg", "100 kg+"
        )

        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)
        val genderErrorText: TextView = findViewById(R.id.genderErrorText)
        val maritalStatusSpinner: Spinner = findViewById(R.id.statusSpinner)
        val maritalStatusErrorText: TextView = findViewById(R.id.statusErrorText)
        val bloodSpinner: Spinner = findViewById(R.id.bloodSpinner)
        val bloodErrorText: TextView = findViewById(R.id.bloodGroupErrorText)
        val heightSpinner: Spinner = findViewById(R.id.heightSpinner)
        val weightSpinner: Spinner = findViewById(R.id.weightSpinner)
        val heightErrorText: TextView = findViewById(R.id.heightErrorText)
        val weightErrorText: TextView = findViewById(R.id.weightErrorText)

         fun createSpinnerAdapter1(
            context: Context,
            options: List<String>
        ): ArrayAdapter<String> {
            return object :
                ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, options) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent) as TextView
                    view.setTextColor(if (position == 0) Color.GRAY else Color.BLACK) // Gray for hint
                    return view
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view = super.getDropDownView(position, convertView, parent) as TextView
                    view.setTextColor(if (position == 0) Color.GRAY else Color.BLACK) // Gray for hint
                    return view
                }

                override fun isEnabled(position: Int): Boolean {
                    return position != 0 // Disable the first item (hint)
                }
            }.apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }


        genderSpinner.adapter = createSpinnerAdapter1(this, genderOptions)
        maritalStatusSpinner.adapter = createSpinnerAdapter1(this, maritalStatusOptions)
        bloodSpinner.adapter = createSpinnerAdapter1(this, bloodOptions)
        heightSpinner.adapter = createSpinnerAdapter1(this, heightOptions)
        weightSpinner.adapter = createSpinnerAdapter1(this, weightOptions)



        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // First item (hint) is selected
                    genderErrorText.visibility = View.GONE
                } else {
                    // A valid gender is selected
                    genderErrorText.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Add custom touch listener to prevent dropdown during scrolling
        genderSpinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Open the dropdown only when the user explicitly taps the Spinner
                genderSpinner.performClick()
            }
            true
        }




        maritalStatusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // First item (hint) is selected
                    maritalStatusErrorText.visibility = View.GONE
                } else {
                    // A valid gender is selected
                    maritalStatusErrorText.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Add custom touch listener to prevent dropdown during scrolling
        maritalStatusSpinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Open the dropdown only when the user explicitly taps the Spinner
                maritalStatusSpinner.performClick()
            }
            true
        }


        bloodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // First item (hint) is selected
                    bloodErrorText.visibility = View.GONE
                } else {
                    // A valid gender is selected
                    bloodErrorText.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Add custom touch listener to prevent dropdown during scrolling
        bloodSpinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Open the dropdown only when the user explicitly taps the Spinner
                bloodSpinner.performClick()
            }
            true
        }


        heightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // First item (hint) is selected
                    heightErrorText.visibility = View.GONE
                } else {
                    // A valid gender is selected
                    heightErrorText.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Add custom touch listener to prevent dropdown during scrolling
        heightSpinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Open the dropdown only when the user explicitly taps the Spinner
                heightSpinner.performClick()
            }
            true
        }

        weightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // First item (hint) is selected
                    weightErrorText.visibility = View.GONE
                } else {
                    // A valid gender is selected
                    weightErrorText.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Add custom touch listener to prevent dropdown during scrolling
        weightSpinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Open the dropdown only when the user explicitly taps the Spinner
                weightSpinner.performClick()
            }
            true
        }


        locationEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val location = s.toString().trim()
                if (location.isEmpty()) {
                    locationErrorText.visibility = View.VISIBLE
                    locationErrorText.text = "Please enter your location."
                } else if (isValidLocation(location)) {
                    locationErrorText.visibility = View.GONE
                } else {
                    locationErrorText.visibility = View.GONE
                   // locationErrorText.text = "Invalid location format."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        nextBtn.setOnClickListener {
            val name = nameEdt.text.toString().trim()
            val email = emailEdt.text.toString().trim()
            val phone = phoneEdt.text.toString().trim()
            val emergencyPhone = emergencyPhoneEdt.text.toString().trim()
            val dob = dobTextView.text.toString().trim()
            val selectedGender = genderSpinner.selectedItem.toString().trim()
            val bloodGroup = bloodSpinner.selectedItem.toString().trim()
            val maritalStatus = maritalStatusSpinner.selectedItem.toString().trim()
            val height = heightSpinner.selectedItem.toString().trim()
            val weight = weightSpinner.selectedItem.toString().trim()
            val location = locationEdt.text.toString().trim()

            var isValid = true

            val capitalizedName = capitalizeWords(name)
            nameEdt.setText(capitalizedName)

            val nameError = isValidName(capitalizedName)
            if (nameError != null) {
                nameEdt2.visibility = View.VISIBLE
                nameEdt2.text = nameError
                isValid = false
            } else {
                nameEdt2.visibility = View.GONE
            }

            val emailError = isValidEmail(email)
            if (emailError != null) {
                emailEdt2.visibility = View.VISIBLE
                emailEdt2.text = emailError
                isValid = false
            } else {
                emailEdt2.visibility = View.GONE
            }


            val phoneError = isValidPhone(phone)
            if (phoneError != null) {
                phoneEdt2.visibility = View.VISIBLE
                phoneEdt2.text = phoneError
                isValid = false
            } else {
                phoneEdt2.visibility = View.GONE
            }


            val emergencyPhoneError = isValidPhone(emergencyPhone)
            if (emergencyPhoneError != null) {
                emergencyphoneErrorText.visibility = View.VISIBLE
                emergencyphoneErrorText.text = phoneError
                isValid = false
            } else {
                emergencyphoneErrorText.visibility = View.GONE
            }

            if (dob == "Select Date of Birth") {
                dobErrorText.visibility = View.VISIBLE
                dobErrorText.text = "Please select your Date of Birth."
                isValid = false
            } else {
                dobErrorText.visibility = View.GONE
            }

            if (selectedGender == "Select Gender") {
                genderErrorText.visibility = View.VISIBLE
                genderErrorText.text = "Please select a gender."
                isValid = false
            } else {
                genderErrorText.visibility = View.GONE
            }

            if (bloodGroup == "Select Blood Group") {
                bloodErrorText.visibility = View.VISIBLE
                bloodErrorText.text = "Please select a blood group."
                isValid = false
            } else {
                bloodErrorText.visibility = View.GONE
            }

            if (maritalStatus == "Select Marital Status") {
                statusErrorText.visibility = View.VISIBLE
                statusErrorText.text = "Please select a marital status."
                isValid = false
            } else {
                statusErrorText.visibility = View.GONE
            }

            if (height == "Select Height") {
                heightErrorText.visibility = View.VISIBLE
                heightErrorText.text = "Please select a height."
                isValid = false
            } else {
                heightErrorText.visibility = View.GONE
            }

            if (weight == "Select Weight") {
                weightErrorText.visibility = View.VISIBLE
                weightErrorText.text = "Please select a weight."
                isValid = false
            } else {
                weightErrorText.visibility = View.GONE
            }

            if (location.isEmpty()) {
                locationErrorText.visibility = View.VISIBLE
                locationErrorText.text = "Please enter your location."
                isValid = false
            } else {
                locationErrorText.visibility = View.GONE
            }


            if (isValidPhone(phone) != null) {
                phoneEdt2.visibility = View.VISIBLE
                phoneEdt2.text = isValidPhone(phone)  // Show specific error message
                isValid = false
            } else {
                phoneEdt2.visibility = View.GONE
            }

            if (isValidEmergencyPhone(phone) != null) {
                emergencyphoneErrorText.visibility = View.VISIBLE
                emergencyphoneErrorText.text = isValidPhone(phone)  // Show specific error message
                isValid = false
            } else {
                emergencyphoneErrorText.visibility = View.GONE
            }


            if (!isValidLocation(location) && !location.isEmpty()) {
                locationErrorText.visibility = View.VISIBLE
                locationErrorText.text = "Invalid location."
                isValid = false
            }

            if (isValid) {
                val intent = Intent(this, NextActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showDatePicker(dobTextView: TextView, dobErrorText: TextView) {
        val calendar = android.icu.util.Calendar.getInstance()
        val year = calendar.get(android.icu.util.Calendar.YEAR)
        val month = calendar.get(android.icu.util.Calendar.MONTH)
        val day = calendar.get(android.icu.util.Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDob = "$selectedDay/${selectedMonth + 1}/$selectedYear"  // Save selected DOB
            dobTextView.text = selectedDob
            dobTextView.setTextColor(ContextCompat.getColor(this, R.color.black)) // Change text color after selection

            // Hide error when DOB is selected
            dobErrorText.visibility = View.GONE

        }, year, month, day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()  // Restrict future dates
        datePickerDialog.show()
    }

    fun isValidName(name: String?): String? {
        if (name.isNullOrEmpty()) {
            return "Please enter your name."
        }
        if (!name.matches(Regex("^[a-zA-Z\\s]+$"))) { // Allows only letters and spaces
            return "Only characters and spaces are allowed."
        }
        return null // Valid name
    }

    fun capitalizeWords(name: String): String {
        return name.split("\\s+".toRegex()) // Split by spaces
            .joinToString(" ") { it.replaceFirstChar { char -> char.uppercaseChar() } } // Capitalize first letter
    }

    fun isValidEmail(email: String?): String? {
        if (email.isNullOrEmpty()) {
            return "Please enter your email."
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Please enter a valid email id."
        }

        val domainPattern = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+\$")
        if (!domainPattern.matches(email)) {
            return "Please enter a valid email id."
        }

        return null
    }

    fun isValidPhone(phone: String?): String? {
        if (phone.isNullOrEmpty()) {
            return "Please enter your phone number."
        }
        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            return "Please enter a valid 10-digit number."
        }
        return null
    }

    fun isValidEmergencyPhone(phone: String?): String? {
        if (phone.isNullOrEmpty()) {
            return "Please enter your phone number."
        }
        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            return "Please enter a valid 10-digit number."
        }
        return null
    }

    fun isValidLocation(location: String): Boolean {
        val locationRegex = "^[A-Za-z0-9][A-Za-z0-9 ',.-]{2,99}$"
        return location.matches(Regex(locationRegex))
    }

    private fun showImageOptions() {
        val hasImage = photoUri != null
        val options = if (hasImage) {
            arrayOf("Open Camera", "Choose from Gallery", "Delete Image", "Cancel")
        } else {
            arrayOf("Open Camera", "Choose from Gallery")
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select an Option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> checkPermissions() // Open Camera
                1 -> openGallery() // Choose from Gallery
                2 -> if (hasImage) deleteImage() else dialog.dismiss() // Delete Image or Cancel
                3 -> dialog.dismiss() // Cancel (only for hasImage = true)
            }
        }
        builder.show()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                CAMERA_PERMISSION_REQUEST
            )
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "${applicationContext.packageName}.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
                    profileImageIv.setImageBitmap(imageBitmap)
                    photoUri = Uri.fromFile(File(currentPhotoPath)) // Update photoUri
                }
                REQUEST_IMAGE_PICK -> {
                    val selectedImageUri = data?.data
                    selectedImageUri?.let { uri ->
                        setImage(uri)
                    }
                }
            }
        }
    }

    private fun setImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(profileImageIv)

        photoUri = uri
        cameraIconIv.visibility = View.VISIBLE // Ensure the camera icon is visible
    }

    private fun deleteImage() {
        profileImageIv.setImageResource(R.drawable.circleimage) // Reset to default
        photoUri = null
        cameraIconIv.visibility = View.VISIBLE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
        }
    }
}
