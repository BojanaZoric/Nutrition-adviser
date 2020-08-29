<template>
    <div id="loginForm">
                <input class="form-control" type="text" v-model="username" placeholder="Username" > <br />
                <input class="form-control" type="password" v-model="password" placeholder="Password" >
            <button type="button" class="btn btn-primary" @click="signIn">Sign in</button>

    </div>
</template>

<script>
import {AXIOS} from '../../http-common'

export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: '',
            error: null,
            errorMessage: ''
        }
    },
    methods: {
        signIn() {
            this.error = null
                                console.log(localStorage.getItem('token'));

            const user = {
                'username': this.username,
                'password': this.password
            }

            AXIOS.post('/users/login', user)
                .then(response => {
                    if (response.status == 200){
                        localStorage.setItem('token', response.data.accessToken);
                console.log(response.data);

}
                })
                .catch(err => {
                    if (err.response.status == 400) {
                        this.errorMessage = "Wrong username or password!";
                        this.error = true
                    }
                })
        }
    }
}
</script>

<style scoped>

#loginForm{
  opacity: 0.9;
  display: inline-block;

}

</style>
