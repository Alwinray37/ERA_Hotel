import emailjs from '@emailjs/browser';
emailjs.init(import.meta.env.VITE_EMAILJS_PUBLIC_KEY);

const SERVICE_ID = import.meta.env.VITE_EMAILJS_SERVICE_ID;
const TEMPLATE_ID = import.meta.env.VITE_EMAILJS_TEMPLATE_ID;
const PUBLIC_KEY = import.meta.env.VITE_EMAILJS_PUBLIC_KEY;

const sendConfirmationEmail = async (guest, reservation) => {
  try {
    // console.log("Guest Info:", guest);
    // console.log("Reservation Info:", reservation);
    const templateParams = {
        to_name: guest.name,
        to_email: guest.email,
        reservation_id: reservation.reservationId,
        room_number: reservation.roomNumber,
        check_in: new Date(reservation.startDate).toLocaleDateString(),
        check_out: new Date(reservation.endDate).toLocaleDateString(),
        total_cost: reservation.totalCost
    };
    //degub 
    console.log("Template Params" , templateParams);

    await emailjs.send("service_4cs2fhe", "template_q78l16b", templateParams, "kkRpuyRNSCGihRppW");
    // console.log("Confirmation email sent successfully!");
  } catch (error) {
    console.error("Failed to send email:", error);
  }
};

export default sendConfirmationEmail;