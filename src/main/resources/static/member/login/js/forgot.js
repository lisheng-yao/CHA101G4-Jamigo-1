(function () {
    const forgotbutton = document.querySelector('#forgotbutton');
    const memberEmail=document.querySelector('#memberemail');

    forgotbutton.addEventListener('click', function () {
        fetch(`forgot`, {
            method: 'Post',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberEmail :memberEmail.value
            }),
        })
            .then(response => {
                if (response.ok) {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '已將密碼寄到您的信箱!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: ' 查無此信箱!請先註冊',
                        footer: '<a href=""></a>'
                    })
                }
            })
            .catch(error => {
                console.error('發生錯誤', error);
            });

    });
})();