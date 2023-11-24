Vue.createApp({
    data(){
        return{
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            dni: "",
            birthdate: "",
            phone: "",
            gender: "NONE",
            btnSignInActive: true,
            errorToats: null,
            errorMsg:"",
            showSignUp: false,
        }
    },
    created(){

    },
    methods:{
        //Iniciar sesion con un nuevo cliente
        logIn: function(event){
            event.preventDefault();
                    let config = {
                        headers: {
                            'content-type': 'application/x-www-form-urlencoded'
                        }
                    }
            axios.post('/api/login', `email=${this.email}&password=${this.password}`, config)
                .then((response) => {
                    // Obtén el ancho de la ventana (viewport width)
                    const widthWindow = window.innerWidth;

                    // Define un umbral para determinar si es un dispositivo móvil
                    const smartphoneWindow = 768;

                    if (this.email.includes("@admin.com")) {
                      window.location.href = "/web/manager/manager.html";
                    } else if(widthWindow < smartphoneWindow) {
                      window.location.href = "/web/exercises.html";
                    }else{
                      window.location.href = "/web/forbidden.html";
                    }
                  })
                .catch(() => {
                    this.errorMsg = "Error en inicio de sesion, verifique la informacion"
                    this.errorToats.show();
                })
        },

        //Registrar un cliente nuevo
        register: function (event) {
                    this.btnSignInActive = false;
                    event.preventDefault();
                    let config = {
                        headers: {
                            'content-type': 'application/x-www-form-urlencoded'
                        }
                    }
                    axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}&rolType=CLIENT&dni=${this.dni}&birthdate=${this.birthdate}&phone=${this.phone}&gender=${this.gender}`, config)
                        .then(() => {
                            this.logIn(event)
                            this.errorMsg = response.text();
                            this.errorToats.show();
                        })
                        .catch(error => {
                            this.btnSignInActive = true;
                            if (error.response && error.response.status === 403) {
                              // Acceder a la respuesta de error cuando se recibe un código 403
                              const errorResponse = error.response;
                              if (errorResponse.data) {
                                // Acceder al texto de respuesta del error
                                const errorText = errorResponse.data;
                                this.errorMsg = errorText;
                                this.errorToats.show();
                              }
                            }
                        })
                        /* .catch(() => {
                            this.errorMsg = response.text();
                            this.errorToats.show();
                        }) */
        },
        chargeData: function (){
            if(this.gender == "none"){
                this.errorMsg = "Campo de genero vacio"
                this.errorMsg.show
                return "error"
            }
        },
        showSignUpToogle: function () {
                    this.showSignUp = !this.showSignUp;
        },

        formatDate: function (date) {
                    return new Date(date).toLocaleDateString('en-gb');
        },
        bottomSignIn: function () {
            this.btnSingInActive = !this.btnSingInActive;
        }

    },
    mounted: function () {
            this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
            }
}).mount("#app")