Vue.createApp({
    data(){
        return{
            clientInfo: [],
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
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
       },
       signOut: function () {
           axios.post('/api/logout')
               .then(response => window.location.href = "/web/index.html")
               .catch(() => {
                   this.errorMsg = "Sign out failed"
                   this.errorToats.show();
               })
       },
    },
    mounted: function () {
            this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
            this.getData();
            }
}).mount("#app")