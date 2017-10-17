<div align="center">
    <form method="post" action="${pageContext.request.contextPath}/registration/">
        <table align="center">

            <tr align="center">
                <th>Login</th>
                <td>
                    <input autofocus type="text" name="username" id="login" placeholder="Login" required
                           oninput="checkLogin(this)">
                </td>
            </tr>
            <script language='javascript' type='text/javascript'>
                function checkLogin(input) {
                    if (!document.getElementById('login').value) {
                        input.setCustomValidity('Enter login.');
                    <%--} else if(${logins.stream()--%>
                            <%--.forEach(log->log!=input.value)}){--%>
                        <%--input.setCustomValidity('This login already exists.');--%>
                    } else {
                        input.setCustomValidity('');
                    }
                }
            </script>

            <tr align="center">
                <th>Password</th>
                <td>
                    <input name="password" required type="password" id="password" placeholder="Password" oninput="checkPassword(this)">
                </td>
            </tr>
            <script language='javascript' type='text/javascript'>
                function checkPassword(input) {
                    if (!document.getElementById('password').value) {
                        input.setCustomValidity('Enter password.');
                    } else {
                        input.setCustomValidity('');
                    }
                }
            </script>
            <tr align="center">
                <th>Repeat password</th>
                <td>
                    <input name="password_confirm" required type="password" id="password_confirm"
                           placeholder="Password" oninput="checkPasswordToMatching(this)">
                </td>
            </tr>
            <script language='javascript' type='text/javascript'>
                function checkPasswordToMatching(input) {
                    if (!document.getElementById('password_confirm').value || input.value != document.getElementById('password').value) {
                        input.setCustomValidity('Password Must be Matching.');
                    } else {
                        input.setCustomValidity('');
                    }
                }
            </script>

            <tr align=" center">
                <th>email</th>
                <td>
                    <input type="email" name="contact.email" id="email" placeholder="Email" required
                           oninput="checkEmail(this)">
                </td>
            </tr>
            <script language='javascript' type='text/javascript'>
                function checkEmail(input) {
                    var reEmail = /^(?:[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+\.)*[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+@(?:(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!\.)){0,61}[a-zA-Z0-9]?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!$)){0,61}[a-zA-Z0-9]?)|(?:\[(?:(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\.){3}(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\]))$/;

                    if (!input.value.match(reEmail)) {
                        input.setCustomValidity('Invalid email address');
                    }else {
                        input.setCustomValidity('');
                    }
                }
            </script>
            <tr></tr>
            <tr align="center">
                <td>
                    <input type="submit" value="Register">

                </td>
            </tr>
        </table>
    </form>
</div>