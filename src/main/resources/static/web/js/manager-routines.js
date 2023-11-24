Vue.createApp({
    data() {
        return {
            routines: [],
            search: '',
            name: '',
            description: '',
            creator: '',
            objective: '',
            exercises: [],
            exercisesSelected: [],
            exercisesSearch: [],
            errorToats: null,
            errorMsg: null,
            okmodal: null,
            modal: null
        }
    },
    methods: {
        getDataExercises: function (){
            axios.get("/api/exercises")
                .then((response) => {
                    //obtiene los datos de todos los ejercicios
                    this.exercises = response.data;
                    this.exercisesSearch = this.exercises;
                    console.log(this.exercisesSelected)
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        getDataRoutines: function (){
            axios.get("/api/routines")
                .then((response) => {
                    //obtiene los datos de todas las rutinas
                    this.routines = response.data;
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        creationRoutine: function(){
            let config = {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            }
            axios.post(`/api/routine?name=${this.name}&description=${this.description}&creator=${this.creator}&objective=${this.objective}&exercises=${this.exercisesSelected}`, config)
                .then(response => {
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
        refreshPage: function (){
            location.reload();
        },
        redirectWindow: function (routineId){
            window.location.href = "/web/routine.html?id=" + routineId;
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
        searchExercise: function (){
            this.exercisesSearch = this.exercises.filter(exercise => exercise.name.toLowerCase().includes(this.search.toLowerCase()));
            console.log(this.search);
            console.log(this.exercisesSelected);
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.okmodal = new bootstrap.Modal(document.getElementById('okModal'));
        this.modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
        this.getDataExercises();
        this.getDataRoutines();
    }
}).mount('#app')