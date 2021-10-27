function callCityWeather() {

	let currentCity = "Sydney";
	console.log(currentCity);

	$.ajax({
		url: '/idlecherry/getCityWeather',
		data: {
			city: currentCity
		},
		success: function (data) {
			console.log(data)
			console.log(JSON.parse(data))
			console.log(JSON.parse(data).coord)
			console.log(JSON.parse(data).weather[0].description);
			document.getElementById("displayWeather").innerHTML = `<span>Hi, current weather in ${JSON.parse(data).name}:</span>` +
				`<span> ${JSON.parse(data).weather[0].description}</span>,` +
				`<span> ${convertTemp(JSON.parse(data).main.temp)}&#8451;</span>`;
		},
		error: function (xhr, exception, thrownError) {
			console.log(xhr);
			console.log(exception);
			console.log(thrownError);
		}
	});
}

function convertTemp(temp) {
	return (temp - 273.15).toFixed(2);
}

window.onload=callCityWeather;

window.addEventListener("scroll", function () {
	var header = document.querySelector("header");
	header.classList.toggle("sticky", window.scrollY > 0);
});

let products = document.querySelectorAll(".products");
let index = 0;

function prev() {
	products[index].classList.remove("active");
	index = (index + 1 + products.length) % products.length;
	products[index].classList.add("active");
}

function next() {
	products[index].classList.remove("active");
	index = (index - 1 + products.length) % products.length;
	products[index].classList.add("active");
}


