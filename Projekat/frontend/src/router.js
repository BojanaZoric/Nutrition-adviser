import Vue from 'vue'
import Router from 'vue-router'

import editProfile from '@/components/account/editProfile'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/edit-profile',
            component: editProfile
        }

    ]
})
