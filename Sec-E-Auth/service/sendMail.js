const nodemailer = require('nodemailer');
const jwt = require('jsonwebtoken');
const PORT = process.env.PORT;
const JWT_SECRET = process.env.JWT_SECRET;

const sendMail = (email, userName)=>{
    const mailTransport = nodemailer.createTransport({
        service:"gmail",
        auth:{
            user:"test.duck.mail@gmail.com",
            pass:"vgwkyfquwksmbbhh"
        }
    })
    const token = jwt.sign({email}, JWT_SECRET, {expiresIn:"10m"})

    console.log(token);
    

    const verificationLink = `http://localhost:${PORT}/verify?token=${token}`;
    const mail = ({
        from : "test.duck.mail@gmail.com",
        to : email,
        subject : "Verification Mail | Sec-E Auth App",
       html:`<body style="font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;">

  <div style="width: 100%; max-width: 600px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
    <div style="text-align: center; padding: 20px; background-color: #007bff; color: #fff; border-radius: 10px 10px 0 0;">
      <h1>Welcome to Sec-E!</h1>
    </div>
    <div style="padding: 20px;">
      <h2 style="color: #333;">Dear ${userName},</h2>
      <p>Your account has been created successfully!</p>
      <p>Please click on the link below to verify your account:</p>
      <a href=${verificationLink} style="display: inline-block; background-color: #28a745; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-weight: bold; text-align: center; margin-top: 20px;">Verify Your Account</a>
    </div>
    <div style="text-align: center; font-size: 12px; color: #777; margin-top: 30px;">
      <p>Thanks & Regards,</p>
      <p><strong>Team Sec-E</strong></p>
    </div>
  </div>

</body>`
    })

    mailTransport.sendMail(mail, (err)=>{
        if(err){
            console.log("Mail Not Sent ", err)
        }
        else{
            console.log("Mail Sent Success")
        }
    })
}

module.exports = sendMail;