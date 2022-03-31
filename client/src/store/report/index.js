const reportModule = {
    state: {
        currentAppointment: null,
        appointmentReport:{
            examinationInformation : '',
            prescribedProducts:[]
        }
    },

    mutations:{
        setCurrentAppointment(state,payload){
            state.currentAppointment = payload;
        },
        setExaminationInformation(state,payload){
            state.appointmentReport.examinationInformation = payload
        },
        addPrescribedProduct(state,payload){
            state.appointmentReport.prescribedProducts.push(payload)
        },
        clearPrescribedProducts(state){
            state.appointmentReport.prescribedProducts = []
        },
        clearAppointmentReport(state){
            state.currentAppointment = null
            state.appointmentReport.examinationInformation = ''
            state.appointmentReport.prescribedProducts = []

        }
    }
}

export default reportModule