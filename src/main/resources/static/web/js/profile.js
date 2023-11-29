Vue.createApp({
    data(){
        return{
            clientInfo: [],
            routine: [],
            payments: {},
            errorMsg: "",
            errorToats: null
        }
    },
    created(){

    },
    methods:{
       getData: function (){
            axios.get("/api/clients/current")
                .then((response) => {
                    //obtiene los datos del cliente actual y autenticado
                    this.clientInfo = response.data;
                    this.routine = this.clientInfo.routines[0].name;
                    this.payments = this.clientInfo.payments;

                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
       },
       formatDate: function (date) {
           return new Date(date).toLocaleDateString('en-gb');
       },
       signOut: function () {
           axios.post('/api/logout')
               .then(response => window.location.href = "/web/index.html")
               .catch(() => {
                   this.errorMsg = "Sign out failed"
                   this.errorToats.show();
               })
       },
       pay: function () {
             window.location.href = 'https://mpago.la/2xuFv5d';
       },
       prueba: function (){
            console.log(this.payments);
       }
    },
    mounted: function () {
            this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
            this.getData();
            }
}).mount("#app")