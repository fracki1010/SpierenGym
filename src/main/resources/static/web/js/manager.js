Vue.createApp({
    data() {
        return {
            clientInfo: [],
            clients: [],
            exercises: [],
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function (){
            axios.get("/api/clients/current")
                .then((response) => {
                    //obtiene los datos del cliente actual y autenticado
                    this.clientInfo = response.data;
                    this.exercises = this.clientInfo.routines[0].exercises;

                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        getDataClients: function (){
            axios.get("/api/clients")
                .then((response) => {
                    //obtiene los datos del cliente actual y autenticado
                    this.clients = response.data;
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        redirectWindow: function (clientId){
            window.location.href = "/web/manager/manager-profile.html?id=" + clientId;
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
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
        this.getDataClients();
    }
}).mount('#app')