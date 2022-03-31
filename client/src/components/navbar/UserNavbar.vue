<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/">WEB Prodavac Kupac</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-content" aria-controls="navbar-content" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar-content">
            <ul class="navbar-nav mr-auto">
                <router-link to="/client/products" class="nav-link">Poruči proizvod</router-link>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/client/feedback">Oceni proizvod ili prodavca</router-link>
                </li>
            </ul>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Korisnik
                </a>
                <div class="dropdown-menu dropdown-menu-right bg-dark" aria-labelledby="navbar-dropdown">
                    <router-link class="dropdown-item" to="/client/profile">Profil</router-link>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" @click="logout">Odjavi se</a>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
import { clearJwt } from '../../helpers/jwt.js'
export default {
    methods: {
        logout: function () {
            clearJwt()
            this.$router.push({ name: 'login' })
        },
    }
}
</script>
