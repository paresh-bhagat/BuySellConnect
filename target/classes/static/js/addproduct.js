function addNewDiv() {
    const dynamicDivContainer = document.getElementById('dynamicDivContainer');
    
    // Create a new div element
    const newDiv = document.createElement('div');
    newDiv.className = 'container border rounded-5';
    newDiv.innerHTML = `<div class="row pt-4">
                            <div class="col-2"><p class="fw-light">Title</p></div>
                            <div class="mb-3 pb-2 text-center col-10">
                                <textarea class="form-control" name="featuretitle" rows="1"
                                minlength="1" maxlength="20" required></textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-2"><p class="fw-light">Content</p></div>
                            <div class="mb-3 pb-2 text-center col-10">
                                <textarea class="form-control" name="featurecontent" rows="1"
                                minlength="1" maxlength="70" required></textarea>
                            </div>
                        </div>

                        <button type="button" class="btn btn-outline-danger m-2 btn-sm" onclick="removeDiv(this)">Delete</button>`;
    
    // Append the new div to the dynamicDivContainer
    dynamicDivContainer.appendChild(newDiv);
}

function removeDiv(button) {
    // Get the parent div (the div containing the close button)
    const parentDiv = button.parentElement;
    
    // Remove the parent div from the dynamicDivContainer
    parentDiv.remove();
}

// for selecting cities and state

 // JSON data containing states and cities of India
 const indianCities = {
    "Andhra Pradesh": ["Visakhapatnam", "Vijayawada", "Guntur", "Nellore", "Kurnool", "Kakinada", "Rajahmundry", "Tirupati"],
    "Arunachal Pradesh": ["Itanagar", "Tawang", "Naharlagun", "Pasighat"],
    "Assam": ["Guwahati", "Silchar", "Dibrugarh", "Jorhat", "Tezpur"],
    "Bihar": ["Patna", "Gaya", "Bhagalpur", "Muzaffarpur", "Darbhanga"],
    "Chhattisgarh": ["Raipur", "Bhilai", "Bilaspur", "Durg", "Rajnandgaon"],
    "Goa": ["Panaji", "Vasco da Gama", "Margao", "Mapusa"],
    "Gujarat": ["Ahmedabad", "Surat", "Vadodara", "Rajkot", "Bhavnagar"],
    "Haryana": ["Chandigarh", "Faridabad", "Gurgaon", "Hisar", "Panipat"],
    "Himachal Pradesh": ["Shimla", "Mandi", "Dharamshala", "Solan"],
    "Jharkhand": ["Ranchi", "Jamshedpur", "Dhanbad", "Bokaro", "Hazaribagh"],
    "Karnataka": ["Bengaluru", "Mysuru", "Hubli-Dharwad", "Mangaluru", "Belagavi"],
    "Kerala": ["Thiruvananthapuram", "Kochi", "Kozhikode", "Thrissur", "Kollam"],
    "Madhya Pradesh": ["Bhopal", "Indore", "Jabalpur", "Gwalior", "Ujjain"],
    "Maharashtra": ["Mumbai", "Pune", "Nagpur", "Thane", "Nashik"],
    "Manipur": ["Imphal", "Thoubal", "Bishnupur", "Churachandpur"],
    "Meghalaya": ["Shillong", "Tura", "Jowai", "Nongpoh"],
    "Mizoram": ["Aizawl", "Lunglei", "Champhai", "Serchhip"],
    "Nagaland": ["Kohima", "Dimapur", "Wokha", "Zunheboto"],
    "Odisha": ["Bhubaneswar", "Cuttack", "Rourkela", "Brahmapur", "Sambalpur"],
    "Punjab": ["Chandigarh", "Ludhiana", "Amritsar", "Jalandhar", "Patiala"],
    "Rajasthan": ["Jaipur", "Jodhpur", "Udaipur", "Kota", "Bikaner"],
    "Sikkim": ["Gangtok", "Namchi", "Mangan", "Rangpo"],
    "Tamil Nadu": ["Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Salem"],
    "Telangana": ["Hyderabad", "Warangal", "Nizamabad", "Karimnagar", "Khammam"],
    "Tripura": ["Agartala", "Udaipur", "Dharmanagar", "Kailasahar"],
    "Uttar Pradesh": ["Lucknow", "Kanpur", "Varanasi", "Agra", "Prayagraj"],
    "Uttarakhand": ["Dehradun", "Haridwar", "Roorkee", "Haldwani"],
    "West Bengal": ["Kolkata", "Howrah", "Asansol", "Siliguri", "Durgapur"],
    "Andaman and Nicobar Islands": ["Port Blair", "Car Nicobar", "Diglipur"],
    "Chandigarh": ["Chandigarh"],
    "Dadra and Nagar Haveli and Daman and Diu": ["Daman", "Diu", "Silvassa"],
    "Lakshadweep": ["Kavaratti", "Agatti", "Minicoy"],
    "Delhi": ["New Delhi"],
    "Puducherry": ["Puducherry", "Karaikal", "Mahe", "Yanam"]
};

// Function to populate state dropdown with options
function populateStateDropdown() {
    const stateSelect = document.getElementById("stateSelect");

    for (const state in indianCities) {
        const option = document.createElement("option");
        option.text = state;
        option.value = state;
        stateSelect.appendChild(option);
    }
}

// Function to populate city dropdown based on selected state
function populateCities() {
    const stateSelect = document.getElementById("stateSelect");
    const citySelect = document.getElementById("citySelect");
    const selectedState = stateSelect.value;

    // Clear the city options first
    citySelect.innerHTML = "<option value=''>Select City</option>";

    // Add city options based on the selected state
    if (indianCities[selectedState]) {
        indianCities[selectedState].forEach((city) => {
            const option = document.createElement("option");
            option.text = city;
            option.value = city;
            citySelect.appendChild(option);
        });
    }
}

// Populate the state dropdown when the page loads
populateStateDropdown();