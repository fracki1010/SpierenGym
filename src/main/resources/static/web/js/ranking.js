Vue.createApp({
    data() {
        return {
            ranking: [],
            rankingClients: [],
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function () {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/rankings/${id}`)
                .then((response) => {
                    //obtener los datos del ranking
                    this.ranking = response.data;
                    let rankingData = this.ranking;
                    console.log(this.ranking);
                    /*this.rankingClient = this.ranking.clients.filter(client => client.weightMaxs.find(weight => weight.exercise.id == rankingData.exercise.id) == rankingData.exercise.id)*/
                    this.rankingClients = this.ranking.clients
                      .filter(client => {
                        return client.weightMaxs.some(weight => {
                          return weight.exercise.id === rankingData.exercise.id;
                        });
                      })
                      .sort((a, b) => {
                        // Ordena de mayor a menor en función de los pesos máximos
                        const weightA = a.weightMaxs.find(weight => weight.exercise.id === rankingData.exercise.id)?.weight || 0;
                        const weightB = b.weightMaxs.find(weight => weight.exercise.id === rankingData.exercise.id)?.weight || 0;
                        return weightB - weightA;
                      });
                    console.log(this.rankingClient);

                })
                .catch((error) => {
                    // handle error
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')