

   var arrowList = [[${arrowList}]];
   var servicesList = [[${servicesList}]];

      if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
      var $ = go.GraphObject.make;  // for conciseness in defining templates

      myDiagram =
        $(go.Diagram, "myDiagramDiv",  // must name or refer to the DIV HTML element
          {
            initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
            contentAlignment: go.Spot.Center,  // align document to the center of the viewport
            layout:
              $(go.ForceDirectedLayout,  // automatically spread nodes apart
                { maxIterations: 200, defaultSpringLength: 30, defaultElectricalCharge: 100 })
          });

      // define each Node's appearance
      myDiagram.nodeTemplate =
         $(go.Node, "Spot",
             $(go.Panel,
          // define the node's outer shape, which will surround the TextBlock
        $(go.Shape, "Circle",  // provide a whole-circle background for the chart
            { width: 100, height: 100, strokeWidth: 0, fill: "transparent" }),
          $(go.Shape, { fill: "transparent", stroke: "green", strokeWidth: 8 },
            new go.Binding("geometry", "value", makeArc),
            new go.Binding("stroke", "color1")),
          $(go.Shape, { fill: "transparent", stroke: "red", strokeWidth: 8 },
            new go.Binding("geometry", "value", makeArcRest),
            new go.Binding("stroke", "color2"))
        ),
          $(go.TextBlock,
            { font: "bold 10pt helvetica, bold arial, sans-serif", margin: 4 },
            new go.Binding("text", "text"))
        );

      function makeArc(sweep) {
        return new go.Geometry()
            .add(new go.PathFigure(50, 0)
                .add(new go.PathSegment(go.PathSegment.Arc, -90, sweep, 50, 50, 50, 50)));

      }

      function makeArcRest(sweep) {
        var p = new go.Point(50, 0).rotate(-90+sweep).offset(50, 50);
        return new go.Geometry()
            .add(new go.PathFigure(p.x, p.y)
                .add(new go.PathSegment(go.PathSegment.Arc, sweep-90, 360-sweep, 50, 50, 50, 50)));

      }

      // replace the default Link template in the linkTemplateMap
      myDiagram.linkTemplate =
        $(go.Link,  // the whole link panel
          $(go.Shape,  // the link shape
            { stroke: "blue", strokeWidth: 3  }),
          $(go.Shape,  // the arrowhead
            { toArrow: "standard", stroke: null  }),
          $(go.Panel, "Auto",
            $(go.Shape,  // the label background, which becomes transparent around the edges
              {
                fill: $(go.Brush, "Radial", { 0: "rgb(240, 240, 240)", 0.3: "rgb(240, 240, 240)", 1: "rgba(240, 240, 240, 0)" }),
                stroke: null
              }),
            $(go.TextBlock,  // the label text
              {
                textAlign: "center",
                font: "12pt helvetica, arial, sans-serif",
                stroke: "#555555",
                margin: 4
              },
              new go.Binding("text", "text"))
          )
        );

      // create the model for the concept map
      var nodeDataArray = [ ];

        for (let i = 0; i < servicesList.length; i++) {
            var service = servicesList[i]
              nodeDataArray.push( { key: service.id, text: service.name, value: 360, color1: service.serviceState  } );
            }

      var linkDataArray = [];
      for (let i = 0; i < arrowList.length; i++) {
      var arrow = arrowList[i]
        linkDataArray.push( { from: arrow.from, to: arrow.to, text: arrow.transactionCount  } );
      }

      myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
