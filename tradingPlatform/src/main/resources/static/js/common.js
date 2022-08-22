$(document).click(function (e) {
    var v_id = e.target.id;
    var v_action = document.getElementById(v_id);
    if(v_id.substr(0,4) == "drop" || v_id.substr(0,6) == "o_drop" || v_id.substr(0,3) == "see") {
        if(v_id.substr(0,6) == "o_drop") {
            if(v_id.charAt(0) == "o") {
                var name_id = "om" + v_id.charAt(v_id.length-1) + "1";
                var price_id = "om" + v_id.charAt(v_id.length-1) + "2";
                var amount_id = "om" + v_id.charAt(v_id.length-1) + "3";
                var seller_id = "om" + v_id.charAt(v_id.length-1) + "4";
                v_action.onclick = function() {
                    $.ajax({
                        url: "oDrop",
                        type: "GET",
                        data: {
                            "ticket_name": document.getElementById(name_id).innerText
                        },
                        contentType: "application/json;charset=utf-8",
                        success: function (data) {
                            if(JSON.parse(data)[0]["index"] == "ok") {
                                alert("票券下架成功!");
                                window.location.reload(true);
                            }else {
                                alert("票券下架失败!");
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            // 状态码
                            console.log(XMLHttpRequest.status);
                            // 状态
                            console.log(XMLHttpRequest.readyState);
                            // 错误信息
                            console.log(textStatus);
                        }
                    });
                };
            }else {
                var name_id = "m" + v_id.charAt(v_id.length-1) + "1";
                var price_id = "m" + v_id.charAt(v_id.length-1) + "2";
                var amount_id = "m" + v_id.charAt(v_id.length-1) + "3";
                var seller_id = "m" + v_id.charAt(v_id.length-1) + "4";
                v_action.onclick = function() {
                    $.ajax({
                        url: "drop",
                        type: "GET",
                        data: {
                            "user_name": document.getElementById("user_name").innerText,
                            "ticket_name": document.getElementById(name_id).innerText,
                            "ticket_price": Number(document.getElementById(price_id).innerText),
                            "ticket_amount": Number(document.getElementById(amount_id).innerText)
                        },
                        contentType: "application/json;charset=utf-8",
                        success: function (data) {
                            if(JSON.parse(data)[0]["index"] == "ok") {
                                alert("票券下架成功!");
                                window.location.reload(true);
                            }else {
                                alert("票券下架失败!");
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            // 状态码
                            console.log(XMLHttpRequest.status);
                            // 状态
                            console.log(XMLHttpRequest.readyState);
                            // 错误信息
                            console.log(textStatus);
                        }
                    });
                };
            };
        }else if(v_action.innerText == "查看") {
            if(v_id.length == 4) {
                var name_id = "om" + v_id.charAt(v_id.length-1) + "1";
                var price_id = "om" + v_id.charAt(v_id.length-1) + "2";
                var amount_id = "om" + v_id.charAt(v_id.length-1) + "3";
                var seller_id = "om" + v_id.charAt(v_id.length-1) + "4";
                v_action.onclick = function() {
                    img_modal.style.display = "block";
                };
                if(img_modal.style.display == "block") {
                    var img = document.getElementById(name_id).innerText;
                    document.getElementById("img").src = "../upload/" + img + ".jpg";
                }else if(img_modal.style.display == "none") {
                    console.log("img_modal.style.display == none");
                };
            }else if(v_id.length == 5) {
                var name_id = "m" + v_id.charAt(v_id.length-1) + "1";
                var price_id = "m" + v_id.charAt(v_id.length-1) + "2";
                var amount_id = "m" + v_id.charAt(v_id.length-1) + "3";
                var seller_id = "m" + v_id.charAt(v_id.length-1) + "4";
                v_action.onclick = function() {
                    img_modal.style.display = "block";
                };
                if(img_modal.style.display == "block") {
                    var img = document.getElementById(name_id).innerText;
                    document.getElementById("img").src = "../upload/" + img + ".jpg";
                }else if(img_modal.style.display == "none") {
                    console.log("img_modal.style.display == none");
                };
            };
        };
    }
});
document.getElementById("img_close").onclick = function() {
    img_modal.style.display = "none";
};
window.onclick = function(event){
    if(event.target == img_modal){
        img_modal.style.display = "none"
    }
};