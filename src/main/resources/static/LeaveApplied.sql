INSERT INTO `CorpConnect`.`COMPANY_EMAIL_TEMPLATES`
(`id`, `name`, `subject`, `body`, `deleted`, `created_date`, `created_by`, `last_updated_date`, `last_updated_by`)
VALUES
(4,
 'leave_applied',
 'Leave Application Submitted',
 '<!DOCTYPE html>
  <html lang=\"en\">
  <head>
      <meta charset=\"UTF-8\" />
      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
      <link href=\"https://fonts.googleapis.com/css2?family=Lato:wght@400;500;600&display=swap\" rel=\"stylesheet\" />
      <title>Leave Application</title>
      <style>
          body {
              font-family: \"Lato\", sans-serif;
              margin: 0;
              padding: 0;
              background-color: #f4f4f4;
              font-size: 14px;
          }
          .container {
              max-width: 600px;
              margin: 0 auto;
              background-color: #ffffff;
              padding: 20px;
              border-radius: 8px;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          }
          h1 {
              color: #333333;
              font-size: 24px;
              text-align: center;
          }
          .main-section h1 {
              margin-top: 30px;
              margin-bottom: 30px;
              color: #cecece;
              font-size: 20px;
              text-align: center;
          }
          .main-section .application {
              padding-top: 10px;
              padding-bottom: 10px;
              font-size: 14px;
          }
          .highlight-text {
              font-weight: 600;
          }
          .highlight-text.italic-text {
              font-style: italic;
          }
          .main-section .application .info {
              padding: 10px 0;
              margin-bottom: 20px;
              color: #666666;
              line-height: 1.6;
          }
          .main-section p {
              margin-bottom: 40px;
          }
          p {
              color: #666666;
              line-height: 1.6;
              text-align: center;
          }
          .leave-header-section {
              background: #56ab2f;
              background: -webkit-linear-gradient(to left, #a8e063, #56ab2f);
              background: linear-gradient(to left, #a8e063, #56ab2f);
              color: white;
              padding: 20px;
              text-align: left;
              border-radius: 8px 8px 0 0;
              text-transform: uppercase;
              margin-bottom: 20px;
          }
          .leave-header-section h2 {
              font-size: 22px;
              margin: 0;
          }
          .details {
              margin-top: 20px;
              padding: 10px;
              background-color: #f9f9f9;
              border-radius: 5px;
              box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
          }
          .details p {
              margin: 8px 0;
              color: #333333;
          }
          .button {
              display: inline-block;
              background: #56ab2f;
              color: white;
              padding: 10px 20px;
              text-align: center;
              text-decoration: none;
              border-radius: 5px;
          }
          .footer {
              margin-top: 20px;
              text-align: center;
              color: #999999;
          }
          .footer p {
              font-size: 12px;
          }
          .auto-message {
              margin-top: 20px;
              font-size: 12px;
              text-align: center;
              color: #999999;
          }
      </style>
  </head>
  <body>
      <div class=\"container\">
          <div class=\"leave-header-section\">
              <h2>Leave Application Received</h2>
          </div>
          <div class=\"main-section\">
              <div class=\"application\">
                  Hi, <span class=\"highlight-text italic-text\">{{employeeName}}</span>.
                  <div class=\"info\">Your {{leaveDaysInfo}} leave application {{leaveDatesInfo}} has been successfully submitted.</div>
                  <a href=\"{{userLeaveRedirection}}\" class=\"button\">View Leave Application</a>
              </div>
              <p>If you have any query regarding this, contact the HR department.</p>
          </div>
          <div class=\"footer\">
              <p>
                  &copy; {{currentYear}} {{companyName}}. All rights reserved.
              </p>
              <p class=\"auto-message\">
                  This is an autogenerated email, please do not reply.
              </p>
          </div>
      </div>
  </body>
  </html>',
 0,
 NOW(),
 'system',
 NOW(),
 'system');
