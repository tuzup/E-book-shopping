/*-----------------------customer homepage----------*/
function paymentValidate() {
	var name = document.forms["payment"]["firstname"].value;
	var email = document.forms["payment"]["email"].value;
	var address = document.forms["payment"]["address"].value;
	var city = document.forms["payment"]["city"].value;
	var state = document.forms["payment"]["state"].value;
	var zip = document.forms["payment"]["zip"].value;
	var cardName = document.forms["payment"]["cardName"].value;
	var cardNumber = document.forms["payment"]["cardNumber"].value;
	var expMonth = document.forms["payment"]["expMonth"].value;
	var expYear = document.forms["payment"]["expYear"].value;
	var cvv = document.forms["payment"]["cvv"].value;
	var returnFlag = true;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	today = new Date();
	someday = new Date();
	someday.setFullYear(expYear, expMonth, 1);

	if (name == "") {
		document.forms["payment"]["firstname"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(name)) {
		document.forms["payment"]["firstname"].style.borderColor = "red";
		document.getElementById('validationFirstName').innerHTML = "*Please enter valid name.";
		returnFlag = false;
	} else {
		document.forms["payment"]["firstname"].style.borderColor = "#ccc";
		document.getElementById('validationFirstName').innerHTML = "";
	}

	if (email == "") {
		document.forms["payment"]["email"].style.borderColor = "red";
		returnFlag = false;
	} else if (!email.match(mailformat)) {
		document.forms["payment"]["email"].style.borderColor = "red";
		document.getElementById('validationEmail').innerHTML = "*Please enter valid email.";
		returnFlag = false;
	} else {
		document.forms["payment"]["email"].style.borderColor = "#ccc";
		document.getElementById('validationEmail').innerHTML = "";
	}

	if (address == "") {
		document.forms["payment"]["address"].style.borderColor = "red";
		returnFlag = false;
	} else {
		document.forms["payment"]["address"].style.borderColor = "#ccc";
	}

	if (city == "") {
		document.forms["payment"]["city"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(city)) {
		document.forms["payment"]["city"].style.borderColor = "red";
		document.getElementById('validationCity').innerHTML = "*Please enter valid city.";
		returnFlag = false;
	} else {
		document.forms["payment"]["city"].style.borderColor = "#ccc";
		document.getElementById('validationCity').innerHTML = "";
	}

	if (state == "") {
		document.forms["payment"]["state"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(state)) {
		document.forms["payment"]["state"].style.borderColor = "red";
		document.getElementById('validationState').innerHTML = "*Please enter valid state.";
		returnFlag = false;
	} else {
		document.forms["payment"]["state"].style.borderColor = "#ccc";
		document.getElementById('validationState').innerHTML = "";
	}

	if (zip == "") {
		document.forms["payment"]["zip"].style.borderColor = "red";
		returnFlag = false;
	} else if (zip.length < 5 || zip.length > 8) {
		document.forms["payment"]["zip"].style.borderColor = "red";
		document.getElementById('validationZip').innerHTML = "*Please enter valid zip.";
		returnFlag = false;
	} else if (/[^0-9]/.test(zip)) {
		document.forms["payment"]["zip"].style.borderColor = "red";
		document.getElementById('validationZip').innerHTML = "*Please enter valid zip.";
		returnFlag = false;
	} else {
		document.forms["payment"]["zip"].style.borderColor = "#ccc";
		document.getElementById('validationZip').innerHTML = "";
	}

	if (cardName == "") {
		document.forms["payment"]["cardName"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(cardName)) {
		document.forms["payment"]["cardName"].style.borderColor = "red";
		document.getElementById('validationCardName').innerHTML = "*Please enter valid cardName.";
		returnFlag = false;
	} else {
		document.forms["payment"]["cardName"].style.borderColor = "#ccc";
		document.getElementById('validationCardName').innerHTML = "";
	}

	if (cardNumber == "") {
		document.forms["payment"]["cardNumber"].style.borderColor = "red";
		returnFlag = false;
	} else if (cardNumber.length < 16 || cardNumber.length > 16) {
		document.forms["payment"]["cardNumber"].style.borderColor = "red";
		document.getElementById('validationCardNumber').innerHTML = "*Please enter valid card number.";
		returnFlag = false;
	} else if (/[^0-9]/.test(cardNumber)) {
		document.forms["payment"]["cardNumber"].style.borderColor = "red";
		document.getElementById('validationCardNumber').innerHTML = "*Please enter valid card number.";
		returnFlag = false;
	} else {
		document.forms["payment"]["cardNumber"].style.borderColor = "#ccc";
		document.getElementById('validationCardNumber').innerHTML = "";
	}

	if (expMonth == "") {
		document.forms["payment"]["expMonth"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^0-9]/.test(expMonth)) {
		document.forms["payment"]["expMonth"].style.borderColor = "red";
		document.getElementById('validationMonth').innerHTML = "*Please enter valid month.";
		returnFlag = false;
	} else if ((expMonth < 1) || (expMonth > 12) || (expMonth.length !== 2)) {
		document.forms["payment"]["expMonth"].style.borderColor = "red";
		document.getElementById('validationMonth').innerHTML = "*Please enter valid month.";
		returnFlag = false;
	} else {
		document.forms["payment"]["expMonth"].style.borderColor = "#ccc";
		document.getElementById('validationMonth').innerHTML = "";
	}

	if (expYear == "") {
		document.forms["payment"]["expYear"].style.borderColor = "red";
		returnFlag = false;
	} else if (someday <= today) {
		document.forms["payment"]["expMonth"].style.borderColor = "red";
		document.forms["payment"]["expYear"].style.borderColor = "red";
		document.getElementById('validationMonthYear').innerHTML = "*Please enter valid month and year.";
		returnFlag = false;
	} else if (expYear.length < 4 || expYear.length > 4) {
		document.forms["payment"]["expYear"].style.borderColor = "red";
		document.getElementById('validationYear').innerHTML = "*Please enter valid year.";
		returnFlag = false;
	} else if (/[^0-9]/.test(expYear)) {
		document.forms["payment"]["expYear"].style.borderColor = "red";
		document.getElementById('validationYear').innerHTML = "*Please enter valid year.";
		returnFlag = false;
	} else {
		document.forms["payment"]["expYear"].style.borderColor = "#ccc";
		document.getElementById('validationYear').innerHTML = "";
		document.getElementById('validationMonthYear').innerHTML = "";
	}

	if (cvv == "") {
		document.forms["payment"]["cvv"].style.borderColor = "red";
		returnFlag = false;
	} else if (cvv.length < 3 || cvv.length > 3) {
		document.forms["payment"]["cvv"].style.borderColor = "red";
		document.getElementById('validationCvv').innerHTML = "*Please enter valid cvv.";
		returnFlag = false;
	} else if (/[^0-9]/.test(cvv)) {
		document.forms["payment"]["cvv"].style.borderColor = "red";
		document.getElementById('validationCvv').innerHTML = "*Please enter valid cvv.";
		returnFlag = false;
	} else {
		document.forms["payment"]["cvv"].style.borderColor = "#ccc";
		document.getElementById('validationCvv').innerHTML = "";
	}

	if (!returnFlag) {
		document.getElementById('validationMessage').innerHTML = "*Please update the highlighted mandatory field(s).";
	}
	return returnFlag;
}

function addressValidate() {
	var name = document.forms["cashOnDelivery"]["firstname"].value;
	var email = document.forms["cashOnDelivery"]["email"].value;
	var address = document.forms["cashOnDelivery"]["address"].value;
	var city = document.forms["cashOnDelivery"]["city"].value;
	var state = document.forms["cashOnDelivery"]["state"].value;
	var zip = document.forms["cashOnDelivery"]["zip"].value;

	var returnFlag = true;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if (name == "") {
		document.forms["cashOnDelivery"]["firstname"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(name)) {
		document.forms["cashOnDelivery"]["firstname"].style.borderColor = "red";
		document.getElementById('validationName').innerHTML = "*Please enter valid name.";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["firstname"].style.borderColor = "#ccc";
		document.getElementById('validationName').innerHTML = "";
	}

	if (email == "") {
		document.forms["cashOnDelivery"]["email"].style.borderColor = "red";
		returnFlag = false;
	} else if (!email.match(mailformat)) {
		document.forms["cashOnDelivery"]["email"].style.borderColor = "red";
		document.getElementById('validationEmail').innerHTML = "*Please enter valid email.";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["email"].style.borderColor = "#ccc";
		document.getElementById('validationEmail').innerHTML = "";
	}

	if (address == "") {
		document.forms["cashOnDelivery"]["address"].style.borderColor = "red";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["address"].style.borderColor = "#ccc";
	}

	if (city == "") {
		document.forms["cashOnDelivery"]["city"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(city)) {
		document.forms["cashOnDelivery"]["city"].style.borderColor = "red";
		document.getElementById('validationCity').innerHTML = "*Please enter valid city.";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["city"].style.borderColor = "#ccc";
		document.getElementById('validationCity').innerHTML = "";
	}

	if (state == "") {
		document.forms["cashOnDelivery"]["state"].style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(state)) {
		document.forms["cashOnDelivery"]["state"].style.borderColor = "red";
		document.getElementById('validationState').innerHTML = "*Please enter valid state.";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["state"].style.borderColor = "#ccc";
		document.getElementById('validationState').innerHTML = "";
	}

	if (zip == "") {
		document.forms["cashOnDelivery"]["zip"].style.borderColor = "red";
		returnFlag = false;
	} else if (zip.length < 5 || zip.length > 8) {
		document.forms["cashOnDelivery"]["zip"].style.borderColor = "red";
		document.getElementById('validationZip').innerHTML = "*Please enter valid zip.";
		returnFlag = false;
	} else if (/[^0-9]/.test(zip)) {
		document.forms["cashOnDelivery"]["zip"].style.borderColor = "red";
		document.getElementById('validationZip').innerHTML = "*Please enter valid zip.";
		returnFlag = false;
	} else {
		document.forms["cashOnDelivery"]["zip"].style.borderColor = "#ccc";
		document.getElementById('validationZip').innerHTML = "";
	}

	if (!returnFlag) {
		document.getElementById('validationMessage').innerHTML = "*Please update the highlighted mandatory field(s).";
	}
	return returnFlag;
}

function validateLogin() {
	var email = document.forms["loginForm"]["email"].value;
	var userPassword = document.forms["loginForm"]["password"].value;
	var returnFlag = true;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (userPassword == "") {
		document.forms["loginForm"]["password"].style.borderColor = "red";
		returnFlag = false;
	} else {
		document.forms["loginForm"]["password"].style.borderColor = "transparent";
	}
	if (email == "") {
		document.forms["loginForm"]["email"].style.borderColor = "red";
		returnFlag = false;
	} else if (!email.match(mailformat)) {
		document.forms["loginForm"]["email"].style.borderColor = "red";
		returnFlag = false;
	} else {
		document.forms["loginForm"]["email"].style.borderColor = "transparent";
	}
	if (!returnFlag) {
		document.getElementById('validationMessage').innerHTML = "*Please update the highlighted mandatory field(s).";
	} else {
		document.getElementById('validationMessage').innerHTML = "";
	}
	return returnFlag;
}

function validateRegister() {
	var firstName = document.forms["userForm"]["firstName"].value;
	var lastName = document.forms["userForm"]["lastName"].value;
	var age = document.forms["userForm"]["age"].value;
	var contactnumber = document.forms["userForm"]["contactNumber"].value;
	var emailId = document.forms["userForm"]["emailId"].value;
	var passWord = document.forms["userForm"]["password"].value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var returnFlag = true;
	if (firstName === "") {
		document.getElementById('firstname').style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(firstName)) {
		document.getElementById('firstname').style.borderColor = "red";
		document.getElementById('validationFirstName').innerHTML = "*Please enter valid First Name.";
		returnFlag = false;
	} else {
		document.getElementById('firstname').style.borderColor = "#ccc";
		document.getElementById('validationFirstName').innerHTML = "";
	}
	if (lastName === "") {
		document.getElementById('lastname').style.borderColor = "red";
		returnFlag = false;
	} else if (/[^a-zA-Z ]/.test(lastName)) {
		document.getElementById('lastname').style.borderColor = "red";
		document.getElementById('validationLastName').innerHTML = "*Please enter valid Last Name.";
		returnFlag = false;
	} else {
		document.getElementById('lastname').style.borderColor = "#ccc";
		document.getElementById('validationLastName').innerHTML = "";
	}

	if (age === "") {
		document.getElementById('age').style.borderColor = "red";
		returnFlag = false;
	} else if (age < 0 || age > 110) {
		document.getElementById('age').style.borderColor = "red";
		document.getElementById('validationAge').innerHTML = "*Please enter valid Age.";
		returnFlag = false;
	} else if (age = /[^0-9]/.test(age)) {
		document.getElementById('age').style.borderColor = "red";
		document.getElementById('validationAge').innerHTML = "*Please enter valid Age.";
		returnFlag = false;
	} else {
		document.getElementById('age').style.borderColor = "#ccc";
		document.getElementById('validationAge').innerHTML = "";
	}

	if (contactnumber === "") {
		document.getElementById('contactNumber').style.borderColor = "red";
		returnFlag = false;
	} else if ((contactnumber.length > 10) || (contactnumber.length < 10)) {
		document.getElementById('contactNumber').style.borderColor = "red";
		document.getElementById('validationContactNumber').innerHTML = "*Please enter valid Contact Number.";
		returnFlag = false;
	} else if (contactnumber = /[^0-9]/.test(contactnumber)) {
		document.getElementById('contactNumber').style.borderColor = "red";
		document.getElementById('validationContactNumber').innerHTML = "*Please enter valid Contact Number.";
		returnFlag = false;
	} else {
		document.getElementById('contactNumber').style.borderColor = "#ccc";
		document.getElementById('validationContactNumber').innerHTML = "";
	}

	if (emailId == "") {
		document.forms["userForm"]["emailId"].style.borderColor = "red";
		returnFlag = false;
	} else if (!emailId.match(mailformat)) {
		document.getElementById('emailId').style.borderColor = "red";
		document.getElementById('validationEmail').innerHTML = "*Please enter valid email.";
		returnFlag = false;
	} else {
		document.getElementById('emailId').style.borderColor = "#ccc";
		document.getElementById('validationEmail').innerHTML = "";
	}

	if (passWord === "") {
		document.getElementById('password').style.borderColor = "red";
		returnFlag = false;
	} else {
		document.getElementById('password').style.borderColor = "#ccc";
	}

	if (!returnFlag) {
		document.getElementById('validationMessage').innerHTML = "*Please update the highlighted mandatory field(s).";
	} else {
		document.getElementById('validationMessage').innerHTML = "";
	}
	return returnFlag;

}

function filterSearch() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("search-bar");
	filter = input.value.toUpperCase();
	table = document.getElementById("book-list");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function validateEditBook() {
	var title = document.forms["editBook"]["title"].value;
	var author = document.forms["editBook"]["author"].value;
	var category = document.forms["editBook"]["category"].value;
	var language = document.forms["editBook"]["Language"].value;
	var publisher = document.forms["editBook"]["publisher"].value;
	var description = document.forms["editBook"]["description"].value;
	var dateOfLaunch = document.forms["editBook"]["dateOfLaunch"].value;
	var binding = document.forms["editBook"]["binding"].value;
	var stock = document.forms["editBook"]["stock"].value;
	var offer = document.forms["editBook"]["offers"].value;
	var price = document.forms["editBook"]["price"].value;
	var res = dateOfLaunch.split("-");
	var regx = /^\d{4}\-\d{1,2}\-\d{1,2}$/;
	var flag = false;
	if (title === "") {
		document.getElementById('title').style.borderColor = "red";
		flag = true;
	}
	if (title !== "") {
		document.getElementById('title').style.borderColor = "#ccc";
	}
	if (author === "") {
		document.getElementById('author').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(author)) {
		document.getElementById('author').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('author').style.borderColor = "#ccc";
	}
	if (category === "") {
		document.getElementById('category').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(category)) {
		document.getElementById('category').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('category').style.borderColor = "#ccc";
	}

	if (language === "") {
		document.getElementById('language').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(language)) {
		document.getElementById('language').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('language').style.borderColor = "#ccc";
	}
	if (publisher === "") {
		document.getElementById('publisher').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(publisher)) {
		document.getElementById('publisher').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('publisher').style.borderColor = "#ccc";
	}
	if (description === "") {
		document.getElementById('description').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('description').style.borderColor = "#ccc";
	}
	if (dateOfLaunch === "") {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if (!(dateOfLaunch.match(regx))) {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if ((res[1] < 01) || (res[1] > 12)) {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if ((res[1] == 01) || (res[1] == 03) || (res[1] == 05)
			|| (res[1] == 07) || (res[1] == 08) || (res[1] == 010)
			|| (res[1] == 12)) {
		if ((res[2] < 01) || (res[2] > 31)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else if ((res[1] == 04) || (res[1] == 06) || (res[1] == 09)
			|| (res[1] == 11)) {
		if ((res[2] < 01) || (res[2] > 30)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else if ((res[1] == 02)) {
		if ((res[2] < 01) || (res[2] > 28)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else {
		document.getElementById('dateOfLaunch').style.borderColor = "#ccc";
	}

	if (binding === "") {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(binding)) {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('binding').style.borderColor = "#ccc";
	}

	if (stock === "") {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else if (stock < 0) {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(stock) === true)) {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else {

		document.getElementById('stock').style.borderColor = "#ccc";

	}

	if (offer === "") {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else if (offer < 0) {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(offer) === true)) {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('offers').style.borderColor = "#ccc";

	}
	if (price === "") {

		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else if (price < 0) {
		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(price) === true)) {
		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else {

		document.getElementById('price').style.borderColor = "#ccc";

	}

	if ((flag === true)) {
		alert('please enter the details correctly');
		return false;
	}
}

function validateAddBook() {
	var title = document.forms["addBook"]["title"].value;
	var author = document.forms["addBook"]["author"].value;
	var category = document.forms["addBook"]["category"].value;
	var language = document.forms["addBook"]["Language"].value;
	var publisher = document.forms["addBook"]["publisher"].value;
	var description = document.forms["addBook"]["description"].value;
	var dateOfLaunch = document.forms["addBook"]["dateOfLaunch"].value;
	var binding = document.forms["addBook"]["binding"].value;
	var stock = document.forms["addBook"]["stock"].value;
	var offer = document.forms["addBook"]["offers"].value;
	var price = document.forms["addBook"]["price"].value;
	var res = dateOfLaunch.split("-");
	var regx = /^\d{4}\-\d{1,2}\-\d{1,2}$/;
	var flag = false;
	if (title === "") {
		document.getElementById('title').style.borderColor = "red";
		flag = true;
	}
	if (title !== "") {
		document.getElementById('title').style.borderColor = "#ccc";
	}
	if (author === "") {
		document.getElementById('author').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z ]/.test(author)) {
		document.getElementById('author').style.borderColor = "red";
		flag = true;

	} else {
		document.getElementById('author').style.borderColor = "#ccc";
	}

	if (category === "") {
		document.getElementById('category').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z]/.test(category)) {
		document.getElementById('category').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('category').style.borderColor = "#ccc";
	}
	if (language === "") {
		document.getElementById('language').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z]/.test(language)) {
		document.getElementById('language').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('language').style.borderColor = "#ccc";
	}
	if (publisher === "") {
		document.getElementById('publisher').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z]/.test(publisher)) {
		document.getElementById('publisher').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('publisher').style.borderColor = "#ccc";
	}

	if (description === "") {
		document.getElementById('description').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('description').style.borderColor = "#ccc";
	}
	if (dateOfLaunch === "") {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if (!(dateOfLaunch.match(regx))) {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if ((res[1] < 01) || (res[1] > 12)) {
		document.getElementById('dateOfLaunch').style.borderColor = "red";
		flag = true;
	} else if ((res[1] == 01) || (res[1] == 03) || (res[1] == 05)
			|| (res[1] == 07) || (res[1] == 08) || (res[1] == 010)
			|| (res[1] == 12)) {
		if ((res[2] < 01) || (res[2] > 31)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else if ((res[1] == 04) || (res[1] == 06) || (res[1] == 09)
			|| (res[1] == 11)) {
		if ((res[2] < 01) || (res[2] > 30)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else if ((res[1] == 02)) {
		if ((res[2] < 01) || (res[2] > 28)) {
			document.getElementById('dateOfLaunch').style.borderColor = "red";
			flag = true;
		}
	} else {
		document.getElementById('dateOfLaunch').style.borderColor = "#ccc";
	}
	if (binding === "") {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z]/.test(binding)) {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('binding').style.borderColor = "#ccc";
	}

	if (stock === "") {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else if (stock < 0) {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(stock) === true)) {
		document.getElementById('stock').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('stock').style.borderColor = "#ccc";
	}

	if (binding === "") {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;
	} else if (/[^a-zA-Z]/.test(binding)) {
		document.getElementById('binding').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('binding').style.borderColor = "#ccc";
	}

	if (offer === "") {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else if (offer < 0) {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(offer) === true)) {
		document.getElementById('offers').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('offers').style.borderColor = "#ccc";
	}

	if (price === "") {
		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else if (price < 0) {
		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else if ((isNaN(price) === true)) {
		document.getElementById('price').style.borderColor = "red";
		flag = true;
	} else {
		document.getElementById('price').style.borderColor = "#ccc";
	}

	if ((flag === true)) {
		alert('please enter the details correctly');
		return false;
	}
}

function validateQuantity() {
	var quantity = document.forms["quantity"]["numberOfBooks"].value;
	flag = true;
	if (quantity == "") {
		document.getElementById('numberOfBooks').style.borderColor = "red";
		flag = false;
	} else if (quantity < 1) {
		document.getElementById('numberOfBooks').style.borderColor = "red";
		flag = false;
	} else if (isNaN(quantity)) {
		document.getElementById('numberOfBooks').style.borderColor = "red";
		flag = false;
	}
	if (flag == false) {
		document.getElementById('noQuantitySelected').innerHTML = "*Please enter a valid quantity";
	}
	return flag;

}

var cartUpdateStatus = false;
function cartUpdated() {
	cartUpdateStatus = true;
}

function proceedPayment() {
	if (cartUpdateStatus) {
		alert("Please Update Cart Before Making Payment !!");
		return false;
	}
}
