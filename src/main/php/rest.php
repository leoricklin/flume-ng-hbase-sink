<html>
 <body>
<?php
  $app_list = file_get_contents('http://{Your Website}/api.php?action=get_app_list');
  $app_list = json_decode($app_list, true);
?>
    <ul>
<?php foreach ($app_list as $app): ?>
      <li>
        <a href=<?php echo "http://{Your Website}/REST_Client.php?action=get_app&id=" . $app["id"]  ?> alt=<?php echo "app_" . $app_["id"] ?>><?php echo $app["name"] ?></a>
    </li>
<?php endforeach; ?>
    </ul>
</body>
</html>