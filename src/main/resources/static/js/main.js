function GenerateEnrollment() {
    var txt = "ACA";
    var random = Math.floor(Math.random() * 1500);
    document.getElementById("enrollment").value = (txt + random);
}