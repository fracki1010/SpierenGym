Vue.createApp({
    data() {
        return {
            message: '',
        }
    },
    methods: {
        uploadImage() {
            const fileInput = this.$refs.fileInput;
            const file = fileInput.files[0];

            if (!file) {
                this.setMessage('Selecciona un archivo para subir');
                return;
            }

            const formData = new FormData();
            formData.append('file', file);

            fetch('/upload-image', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    this.setMessage('Imagen subida con éxito');
                } else {
                    this.setMessage('Error al subir la imagen');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                this.setMessage('Error al subir la imagen');
            });
        },
        setMessage(message) {
            this.message = message;
        }
    },
    mounted: function () {

    }
}).mount('#app')