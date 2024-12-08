function validateForm() {
    const username = document.getElementsByName("username")[0];
    const password = document.getElementsByName("password")[0];
    const submitButton = document.querySelector('input[type="submit"]');

    // 檢查帳號與密碼欄位
    if (username.value.trim() !== "" && password.value.trim() !== "") {
        submitButton.disabled = false; // 啟用按鈕
    } else {
        submitButton.disabled = true; // 禁用按鈕
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const username = document.getElementsByName("username")[0];
    const password = document.getElementsByName("password")[0];

    // 初始化按鈕為禁用狀態
    document.querySelector('input[type="submit"]').disabled = true;

    // 輸入框綁定輸入事件
    username.addEventListener("input", validateForm);
    password.addEventListener("input", validateForm);
});