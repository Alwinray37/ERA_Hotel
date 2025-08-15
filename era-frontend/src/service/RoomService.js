import axios from 'axios';

const urlbase = 'http://localhost:8080/api/rooms';

export const listRooms = async () => {
  try {
    const response = await axios.get(urlbase);
    return response.data;
  } catch (error) {
    console.error('Error fetching room data:', error);
    throw error;
  }
};

export const getRoom = async (roomId) => {
    try{
        const res = await axios.get(`${urlbase}/${roomId}`);
        return res.data;
    }catch(err){
        console.error(err);
    }
}

export const getRoomByNumber = async (roomNum) => {
  try{
    const res = await axios.get(`${urlbase}/room/${roomNum}`);
    return res.data;
  } catch(err){
    console.log(err);
    throw err;
  }
}

export const updateRoom = async (roomId, updatedRoom) => {
    try {
    const response = await axios.put(`${urlbase}/${roomId}`, updatedRoom);
    return response.data;
  } catch (error) {
    console.error('Error updating room:', error);
    throw error;
  }
}

// Add a new room
export async function addRoom(room) {
  try {
      const response = await axios.post(`${urlbase}`, room, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch(err) {
    console.log('Error adding room', err);
  }
}