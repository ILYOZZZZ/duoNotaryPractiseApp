export const api = {
    login: '/auth/login',
    userMe: '/user/me',
    getServices: '/Services',
    getMainService: '/mainServices',
    getPublicHolidays:'/publicHoliday',
    getCompanyWorkTime:'/companyWorkTime',
    changeServiceActive: '/Services/',
    changeMainServiceActive: '/mainServices/active/',
    changePublicHolidayActive:'/publicHoliday/active/',
    changeCompanyWorkTimeActive:'/companyWorkTime/active/',





    getCountiesOfState:'/county',
    editCountyActive:'county/edit/active',
    saveCounty:'/county',
    editCounty:'/county/edit',
    getCurrentCounty:'/county/current',

    getFeedbacks:'/feedback',
    sendFeedbackAnswer:'/feedback/send',

    getStates:'/state',
    getCurrentState:'/state/state',
    saveState:'/state',
    editState:'/state/edit',
    editStateActive:'/state/edit/active',

    getCurrentZipCode:'/zip',
    getCurrentValuesOfZipCodeToEdit:'/zip/state',
    editZipCode:'/zip',
    removeAgent:'/zip/user',
    getAgentToLinkZip:'/zip/agent',
    saveAgentToZip:'/zip/agent/save',
    getAllZipCodes:'/zip',
    saveZipCode:'/zip',







};