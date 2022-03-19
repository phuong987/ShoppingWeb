/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addToCart(productId){
    fetch(`/App/api/cart/${productId}`).then(res => res.json()).then(data =>{
        var d = document.getElementById("cart-counter");
        if (d !== null)
            d.innerText = data;
    });
}

function addComment(productId){
    fetch(`/App/api/add-comment`, {
        method: 'post',
        body: JSON.stringify({
            "content": document.getElementById("commentId").value,
            "productId": productId
        }),
        headers:{
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data){
        console.info(data);
        let area = document.getElementById("commentArea");
        let img;
        if (data.user.avatar !== null)
            img = `<img class="rounded-circle img-fluid" src="${data.user.avatar}" />`;
        else
            img = `<img class="rounded-circle img-fluid" 
    src="https://res.cloudinary.com/dhdw0zzon/image/upload/v1647157330/avatar_tztgqp.jpg" />`;
        area.innerHTML = 
                `<div class="row">
                    <div class="col-md-2" style="padding:10px" >` + img +
                    `</div>
                    <div class="col-md-10 my-date">
                        <p>${data.content}</p>
                        <i>${moment(data.createdDate).fromNow()}</i>
                    </div>
                </div>`
        + area.innerHTML;
    });
}

function updateCart(obj, productId){
    fetch("/App/api/cart",{
        method: 'put',
        body: JSON.stringify({
            "productId": productId,
            "name": "",
            "price": 0,
            "count": obj.value
        }),
        headers:{
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data){
        let counter = document.getElementById("cart-counter");
        counter.innerText = data;
        if (obj.value <= 0){
            let row = document.getElementById(`${productId}`);
            row.style.display = "none";
        }
    });
}

function deleteCart(productId){
    if (confirm("Xoa item?")===true){
        fetch(`/App/api/cart/${productId}`,{
            method: 'delete'
        }).then(function(res){
            return res.json();
        }).then(function(data){
            let counter = document.getElementById("cart-counter");
            counter.innerText = data;
            location.reload();
        });
    }
}