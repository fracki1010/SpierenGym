Vue.createApp({
    data() {
        return {
            clientInfo: [],
            payments: [],
            paymentMethod: '',
            paymentSelected: null,
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function (){
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/clients/${id}`)
                .then((response) => {
                    //obtiene los datos del cliente actual y autenticado
                    this.clientInfo = response.data;

                    //Ordenar los pagos
                    this.payments = this.clientInfo.payments.sort((a, b) => {
                    return new Date(a.paymentDue) - new Date(b.paymentDue);
                    })
                    console.log(this.payments);

                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        modalApproved: function(paymentId){
            this.modal.show();
            this.paymentSelected = paymentId;
        },
        approved: function(){
            axios.post(`/api/payments/approved?paymentId=${this.paymentSelected}&paymentMethod=${this.paymentMethod}`)
                .then((response) => {
                    this.modal.hide();
                    this.okmodal.show();
                })
                .catch((error) => {
                    if (error.response && error.response.status === 403) {
                        const errorResponse = error.response;
                        if (errorResponse.data) {
                            // Acceder al texto de respuesta del error
                            const errorText = errorResponse.data;
                            this.errorMsg = errorText;
                            this.errorToats.show();
                        }
                    }
                })
        },
        refreshPage: function() {
            window.location.reload();
        },
        signOut: function () {
            axios.post('/api/logout')
                .then(response => window.location.href = "/web/index.html")
                .catch(() => {
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
        }
    },
    computed: {
        hasUnapprovedPayments: function() {
            return this.payments.some(payment => !payment.paymentStatus);
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
        this.modal = new bootstrap.Modal(document.getElementById('Modal'));
        this.okmodal = new bootstrap.Modal(document.getElementById('okModal'));
    }
}).mount('#app')