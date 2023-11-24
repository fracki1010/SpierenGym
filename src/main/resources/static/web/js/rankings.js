Vue.createApp({
    data() {
        return {
            rankings: [],
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function (){
            axios.get("/api/rankings")
                .then((response) => {
                    //obtiene los datos de todos los rankings
                    this.rankings = response.data;
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos de rankings";
                    this.errorToats.show();
                })


        },
        redirectWindow: function (dayId){
            window.location.href = "/web/ranking.html?id=" + dayId;
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')