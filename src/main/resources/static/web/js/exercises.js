Vue.createApp({
    data() {
        return {
            clientInfo: [],
            routine: '',
            exercises: {},
            exercise: ' ',
            day: '',
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

                    //Obtener el nombre de la rutina
                    this.routine = this.clientInfo.routines[0];

                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        redirectWindow: function (exerciseId){
            window.location.href = "/web/exercise.html?id=" + exerciseId;
        },
        getDayCurrent() {
          const weekDays = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
          const date = new Date();
          const day = weekDays[date.getDay()];
          this.day = day;
          console.log(this.day);
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
        this.getDayCurrent();
    }
}).mount('#app')