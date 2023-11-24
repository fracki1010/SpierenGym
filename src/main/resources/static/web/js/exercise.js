Vue.createApp({
    data() {
        return {
            clientInfo: [],
            exercises: [],
            exercise: ' ',
            day: '',
            muscle: '',
            nameModal: '',
            container: '',
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getDataClient: function () {
            axios.get('/api/clients/current')
                .then((response) => {
                    this.clientInfo = response.data;
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
        getData: function () {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/exercises/${id}`)
                .then((response) => {
                    //obtener datos del clietn
                    this.exercise = response.data;
                    this.muscle = this.exercise.muscleGroup;
                    console.log(this.muscle);
                })
                .catch((error) => {
                    // handle error
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
        edit: function (name) {
            this.nameModal = name;
            if(name == "Musculo"){
                this.modalMuscle.show();
            }else{
                this.modal.show();
            }
        },
        saveEdit: function (){
            let edit;
            switch (this.nameModal){
                case "Musculo":
                edit = 'Muscle';
                this.container = this.muscle;
                break;
                case "Descripcion":
                edit = "Description";
                break;
                case "Instruccion":
                edit = "Instruction";
                break;
                case "Nombre":
                edit = "Name"
                break;
                default:
                edit = ' ';
                break;
            }
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            let config = {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            }
            axios.post(`/api/exercises/edit/${edit.toLowerCase()}?id=${id}&new${edit}=${this.container}`, config)
                .then(response => {
                    this.modal.hide();
                    this.modalMuscle.hide();
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
        signOut: function () {
            axios.post('/api/logout')
                .then(response => window.location.href = "/web/index.html")
                .catch(() => {
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
        }
        formatDate: function (date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.modal = new bootstrap.Modal(document.getElementById('Modal'));
        this.okmodal = new bootstrap.Modal(document.getElementById('okModal'));
        this.modalMuscle = new bootstrap.Modal(document.getElementById('ModalMuscle'));
        this.getDataClient();
        this.getData();
    }
}).mount('#app')