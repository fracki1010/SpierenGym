Vue.createApp({
    data() {
        return {
            clientInfo: {},
            isExpanded: false,
            exercise: ' ',
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        toggleExpansion() {
            this.isExpanded = !this.isExpanded;
          },
          showExercise(){
            this.exercise = 'exercise'
            this.isExpanded = !this.isExpanded;
          }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')