Vue.createApp({
    data() {
        return {
            day: [],
            dayString: '',
            exercises: [],
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function () {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/routines/weekday/${id}`)
                .then((response) => {
                    //get client info
                    this.day = response.data;
                    this.exercises = this.day;
                    this.dayString = this.daySearch(id);
                })
                .catch((error) => {
                    // handle error
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
        formatDate: function (date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
        daySearch: function (date){
            switch (date){
                case '1':
                    return 'LUNES';
                break;
                case '2':
                    return 'MARTES';
                break;
                case '3':
                    return 'MIERCOLES';
                break;
                case '4':
                    return 'JUEVES';
                break;
                case '5':
                    return 'VIERNES';
                break;
            }
        },
        redirectWindow: function (exerciseId){
            window.location.href = "/web/exercise.html?id=" + exerciseId;
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')