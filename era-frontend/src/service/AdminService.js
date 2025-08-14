import axios from 'axios';

const urlbase = 'http://localhost:8080/api/admins';

export const listAdmins = async () => {
  try {
    const response = await axios.get(urlbase);
    return response.data;
  } catch (error) {
    console.error('Error fetching admin data:', error);
    throw error;
  }
}

// This function is used to create a new admin
// It should be called when a new guest books a room
export const createGuest = async (adminData) => {
    // guest data should just be name, email and phone
    const { name, email, phone } = adminData;
    if (!name || !email || !phone) {
        throw new Error('Invalid admin data');
    }

  try {
    const response = await axios.post(urlbase, guestData);
    return response.data;
  } catch (error) {
    console.error('Error creating admin:', error);
    throw error;
  }

}

// get admin by email
export const getAdminByEmail = async (email) => {
  try {
    const response = await axios.get(`${urlbase}/email/${email}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching admin by email:', error);
    throw error;
  }
}

// update admin information
//export const updateGuest = async (id, guestData) => {
//  try {
//    const response = await axios.put(`${urlbase}/${id}`, adminData);
//    return response.data;
//  } catch (error) {
//    console.error('Error updating admin:', error);
//    throw error;
//  }
//}

// : get reservation summaries for admin dashboard
export const listAdminReservationSummaries = async () => {
  try {
    const response = await axios.get(`${urlbase}/reservations/summary`);
    return response.data;
  } catch (error) {
    console.error('Error fetching admin reservation summary:', error);
    throw error;
  }
}
