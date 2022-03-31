var host = 'http://localhost:8080'
if (process.env.NODE_ENV == 'production')
    host = 'https://isa-pharmacy.herokuapp.com'

//REST endpoints
let auth = "/api/auth"
let appointments = '/api/appointments'
let drugs = '/api/drugs'
let employees = '/api/employees'
let scheduling = '/api/schedule'
let clients = '/api/clients'
let person = '/api/person'
let storedDrugs = '/api/stored-drugs'
let timeOff = '/api/time-off'
let pharmacies = '/api/pharmacies'
let rating = '/api/rating'
let locale = '/api/locale'
let promotion = '/api/promotion'
let complaint = '/api/complaint'
let order = '/api/purchase-order'
let offer = '/api/offer'
let supplier = '/api/supplier'
let admin = '/api/admin'

export const api = {
    auth: { 
        login: host + auth + '/login', 
        hasLoggedBefore: host + auth + '/logged',
        changePassword: host + auth + '/change-password',
        register: host + auth + "/register"
    }
    ,
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
        all: host + appointments + '/all',
        client: host + appointments + '/client',
        cancel: host + appointments + '/cancel',
        upcoming: host + appointments + '/upcoming/',
        appointmentReport: host + appointments + '/appointment-report',
        freeUp: {
            examination: host + appointments + '/examinations/free-up/',
            counseling: host + appointments + '/counselings/free-up/'
        },
        history: {
            examinations: host + appointments + '/examinations/client-history/',
            counselings: host + appointments + '/counselings/client-history/'
        },
        clientHistory: {
            examinations: host + appointments + '/client/examination-history',
            counselings: host + appointments + '/client/counseling-history'
        }
        
    },
    drugs: {
        root: host + drugs,
        search: host + drugs + '/search',
        clientSearch: host + drugs + '/client-search',
        reservations: host + drugs + '/reservations/',
        dispense: host + drugs + '/reservations/dispense/',
        simple: host + drugs + '/simple'
    },
    storedDrugs: {
        root: host + storedDrugs,
        isAvailable : host + storedDrugs + '/is-available'
    },
    employees: {
        registerDermatologist : host + employees + "/register-dermatologist",
        employeeId : host + employees + '/employee-id/',
        myPharmacies: host + employees + '/my-pharmacies/'
    },
    scheduling: {
        root: host + scheduling,
        predefined: host + scheduling + '/predefined',
        newExamination: host + scheduling + '/examination',
        newCounseling: host + scheduling + '/counseling',
        clientCounseling: host + scheduling + '/counseling/client',
        findPharmacists: host + scheduling + '/counseling/pharmacists'
    },
    clients: {
        root: host + clients,
        appointed: host + clients + '/appointed',
        profilePreview: host + clients + '/profile-preview/',
        isAllergic: host + clients + '/is-allergic',
        allergies: host + clients + '/allergies'
    },
    person: {
        root: host + person,
        credentials: host + person + '/credentials',
        name: host + person + '/name'
    },
    timeOff: {
        root: host + timeOff
    },
    pharmacies: {
        root: host + pharmacies,
        addAdmin: host + pharmacies + '/admin'
    },
    rating: {
        root: host + rating,
        employee: host + rating + '/employee',
        drug: host + rating + '/drug',
        pharmacy: host + rating + '/pharmacy'
    },
    locale: {
        root: host + locale,
        cities: host + locale + "/cities",
        countries: host + locale + "/countries"
    },
    promotion: {
        root: host + promotion,
        subscribe: host + promotion + '/subscribe',
        unsubscribe: host + promotion + '/unsubscribe',
        isSubscribed: host + promotion + '/subscribed',
        pharmacies: host + promotion + "/subscribed"
    },
    complaint: {
        root: host + complaint,
        respond: host + complaint + '/respond'
    },
    order: {
        allActive: host + order,
    },
    offer: {
        root: host + offer,
        status: host + offer + "/status",
        check: host + offer + "/check",
    },
    supplier: {
        root: host + supplier,
    },
    admin: {
        root: host + admin
    }
}