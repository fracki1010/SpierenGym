Vue.createApp({
    data() {
        return {
            clientInfo: [],
            routine: [],
            exercises: [],
            monday: [],
            mondayMuscles: '',
            tuesday: [],
            tuesdayMuscles:'',
            wednesday: [],
            wednesdayMuscles: '',
            thursday: [],
            thursdayMuscles: '',
            friday: [],
            fridayMuscles: '',
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function (){
            axios.get("/api/routines/client/current")
                .then((response) => {
                    //obtiene los datos de la rutina del cliente logueado
                    this.routine = response.data;

                    //Filtra cada ejercicio para cada dia
                    //Lunes
                    this.monday = this.routine.exercises.filter(exercise => exercise.weekdays.includes('MONDAY'))
                    let mondayMuscles = new Set(this.monday.map(exercise => exercise.muscleGroup))
                    this.mondayMuscles = Array.from(mondayMuscles).join(', ');
                    //Martes
                    this.tuesday = this.routine.exercises.filter(exercise => exercise.weekdays.includes('TUESDAY'))
                    let tuesdayMuscles = new Set(this.tuesday.map(exercise => exercise.muscleGroup))
                    this.tuesdayMuscles = Array.from(tuesdayMuscles).join(', ');
                    //Miercoles
                    this.wednesday = this.routine.exercises.filter(exercise => exercise.weekdays.includes('WEDNESDAY'))
                    let wednesdayMuscles = new Set(this.wednesday.map(exercise => exercise.muscleGroup))
                    this.wednesdayMuscles = Array.from(wednesdayMuscles).join(', ');
                    //Jueves
                    this.thursday = this.routine.exercises.filter(exercise => exercise.weekdays.includes('THURSDAY'))
                    let thursdayMuscles = new Set(this.thursday.map(exercise => exercise.muscleGroup))
                    this.thursdayMuscles = Array.from(thursdayMuscles).join(', ');
                    //Viernes
                    this.friday = this.routine.exercises.filter(exercise => exercise.weekdays.includes('FRIDAY'))
                    let fridayMuscles = new Set(this.friday.map(exercise => exercise.muscleGroup))
                    this.fridayMuscles = Array.from(fridayMuscles).join(', ');
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })


        },
        getDataExercises: function (){
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            console.log(id);
            axios.get(`/api/routines/${id}`)
                .then((response) => {
                    this.exercises = response.data.exercises;
                    console.log(this.exercises);
                })
                .catch((error) => {
                    // Por si no hay un cliente autenticado y no obtiene nada
                    this.errorMsg = "Error al obtener los datos del cliente";
                    this.errorToats.show();
                })
        },
        getDataClient: function (){
            axios.get("/api/clients/current")
                            .then((response) => {
                                this.clientInfo = response.data;
                            })
                            .catch((error) => {
                                // Por si no hay un cliente autenticado y no obtiene nada
                                this.errorMsg = "Error al obtener los datos del cliente";
                                this.errorToats.show();
                            })
        },
        redirectWindow: function (dayId){
            window.location.href = "/web/routine-day.html?id=" + dayId;
        },
        redirectWindowExercise: function (id){
            window.location.href = "/web/exercise.html?id=" + id;
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
        this.getDataClient();
        this.getDataExercises();
    }
}).mount('#app')